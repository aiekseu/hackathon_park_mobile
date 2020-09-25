package com.sconzo.hackathon_park_mobile.fRegister2

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface RegisterTwoView: MvpView {

    // User Inform

    @OneExecution
    fun showSnackbar(message: String)

    @AddToEndSingle
    fun showProgressBar(isVisible: Boolean)


    // Registration Finish

    @OneExecution
    fun finishRegisterProcess()
}