package com.sconzo.hackathon_park_mobile.fRegister

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface RegisterView: MvpView {

    // Data Errors Show

    @AddToEndSingle
    fun setNameError(message: String)

    @AddToEndSingle
    fun setEmailError(message: String)


    @AddToEndSingle
    fun setPasswordError(message: String)

    @AddToEndSingle
    fun setRepeatPasswordError(message: String)


    // User Inform

    @OneExecution
    fun showSnackbar(message: String)

    @AddToEndSingle
    fun showProgressBar(isVisible: Boolean)


    // Registration Finish

    @OneExecution
    fun finishRegisterProcess()

    @OneExecution
    fun navigateFurther()
}