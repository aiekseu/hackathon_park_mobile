package com.sconzo.hackathon_park_mobile.fRegister2

import com.sconzo.hackathon_park_mobile.Errors
import com.sconzo.hackathon_park_mobile.makeLogError
import com.sconzo.hackathon_park_mobile.model.Model
import com.sconzo.hackathon_park_mobile.model.api.ServerRequest
import com.sconzo.hackathon_park_mobile.model.data.ObjectData
import com.sconzo.hackathon_park_mobile.model.data.User
import com.sconzo.hackathon_park_mobile.model.preferences.ModelPreferences
import com.sconzo.hackathon_park_mobile.throwError
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class RegisterTwoPresenter: MvpPresenter<RegisterTwoView>() {

    private val disposables = CompositeDisposable()
    val model = Model()

    fun onRegister(birthday: String, hasKids: Boolean, sex: String){
        inciteProgressBar(true)
        val user =
            User(email = ObjectData.email, name = ObjectData.name, surname = ObjectData.surname, password = ObjectData.password,
                    birthday = birthday, hasKids = hasKids, sex = sex)

        viewState.showSnackbar(user.toString())
        val disposableRegisterUser = model.registerUser(ServerRequest.registerUser(user))
            .subscribe(
                { response ->
                    inciteProgressBar(false)
                    val pref = model.getPreferences()
                    if (response.result == null || response.result == false) {
                        makeLogError(response.error?.message ?: Errors.ERROR_SERVER)
                        //viewState.showSnackbar(response.error?.message ?: Errors.ERROR_SERVER)
                    } else if (pref == null) {
                        makeLogError(Errors.ERROR_APP)
                        //viewState.showSnackbar(Errors.ERROR_APP)
                    } else {
                        finishRegisterSuccess(pref, user)
                    }
                },
                {
                    throwError(it)
                    viewState.showSnackbar(Errors.ERROR_APP)
                })
        disposables.add(disposableRegisterUser)
    }

    // Register Finish
    private fun finishRegisterSuccess(pref: ModelPreferences, user: User) {
        pref.setIsLoggedIn(true)
        pref.setUser(user)
        viewState.finishRegisterProcess()
    }


    // User Inform
    private fun inciteProgressBar(isVisible: Boolean) {
        viewState.showProgressBar(isVisible)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}