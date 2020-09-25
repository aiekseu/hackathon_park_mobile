package com.sconzo.hackathon_park_mobile.fLogin

import com.sconzo.hackathon_park_mobile.Errors
import com.sconzo.hackathon_park_mobile.model.Model
import com.sconzo.hackathon_park_mobile.model.api.ServerRequest
import com.sconzo.hackathon_park_mobile.model.data.User
import com.sconzo.hackathon_park_mobile.throwError
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {

    val model = Model()
    private val disposables = CompositeDisposable()

    // Login and Password Server Check

    fun loginUser(email: String, password: String) {

        incitePasswordError(false)
        inciteProgressBar(true)
        model.getPreferences().let { pref ->
            val user = User(email = email, password = password)
            val request = ServerRequest.loginUser(user)
            val disposableLoginUser = model.loginUser(request)
                .subscribe(
                    { response ->
                        inciteProgressBar(false)
                        response.result?.let {
                            it.password = user.password
                            it.email = user.email
                            pref.setIsLoggedIn(true)
                            pref.setUser(it)
                            viewState.finishLoginFragment()
                        } ?: incitePasswordError(true)
                    },
                    {
                        throwError(it)
                        incitePasswordError(true)
                        viewState.showSnackbar(Errors.ERROR_APP)
                    }
                )
            disposables.add(disposableLoginUser)
        }
    }


    // Registration Fragment Start

    fun toRegisterFragment() {
        viewState.toRegisterFragment()
    }


    //Incorrect Login or Password Show

    private fun incitePasswordError(isError: Boolean) {
        viewState.showPasswordError(isError)
    }


    // User Inform: Loading Show

    private fun inciteProgressBar(isVisible: Boolean) {
        viewState.showProgressBar(isVisible)
    }


    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}