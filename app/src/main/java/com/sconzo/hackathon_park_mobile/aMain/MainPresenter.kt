package com.sconzo.hackathon_park_mobile.aMain

import com.sconzo.hackathon_park_mobile.Errors
import com.sconzo.hackathon_park_mobile.makeLogDebug
import com.sconzo.hackathon_park_mobile.makeLogError
import com.sconzo.hackathon_park_mobile.model.Model
import com.sconzo.hackathon_park_mobile.model.api.ServerRequest
import com.sconzo.hackathon_park_mobile.model.api.ServerResponse
import com.sconzo.hackathon_park_mobile.model.data.User
import com.sconzo.hackathon_park_mobile.model.preferences.ModelPreferences
import com.sconzo.hackathon_park_mobile.throwError
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter: MvpPresenter<MainView>() {
    private val model = Model()
    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        checkSavedUser()
    }


    // User Login and Password Validate

    private fun checkSavedUser() {
        val pref = model.getPreferences()
        if (pref.getIsLoggedIn()) {
            pref.setIsLoggedIn(false)
            val userPref = pref.getUser()
            val disposableLoginUser = model.loginUser(ServerRequest.loginUser(userPref))
                .subscribe(
                    { response ->
                        response.result?.let {
                            acceptUser(it, userPref, pref)
                        } ?: rejectedUserError(pref, response)
                    },
                    {
                        throwError(it)
                        rejectedUser(pref)
                    })
            disposables.add(disposableLoginUser)
        } else {
            rejectedUser(pref)
        }
    }


    // User Accept

    private fun acceptUser(userServer: User, userPref: User, pref: ModelPreferences) {
        userServer.password = userPref.password
        userServer.email = userPref.email
        pref.setIsLoggedIn(true)
        pref.setUser(userServer)
        makeLogDebug("User from SharedPreferences")
        //viewState.toCartFragment()
    }


    // Server Error

    private fun rejectedUserError(pref: ModelPreferences, response: ServerResponse<User>) {
        makeLogError(response.error?.message ?: Errors.ERROR_SERVER)
        rejectedUser(pref)
    }


    // User Does Not Exist

    private fun rejectedUser(pref: ModelPreferences) {
        pref.clear()
        viewState.toLoginFragment()
    }

    fun toLogin(){
        viewState.toLoginFragment()
    }


    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}