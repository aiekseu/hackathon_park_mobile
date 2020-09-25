package com.sconzo.hackathon_park_mobile.fLogin
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution


interface LoginView : MvpView {

    // Register Fragment Start

    @OneExecution
    fun toRegisterFragment()


    // Successfully Log In

    @OneExecution
    fun finishLoginFragment()


    // Incorrect Login or Password

    @AddToEndSingle
    fun showPasswordError(isError: Boolean)


    // User Inform

    @OneExecution
    fun showSnackbar(message: String)

    @AddToEndSingle
    fun showProgressBar(isVisible: Boolean)
}