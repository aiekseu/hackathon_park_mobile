package com.sconzo.hackathon_park_mobile.fLogin

import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {

    fun loginUser(email: String, password: String) {
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
        //disposables.clear()
    }
}