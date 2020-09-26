package com.sconzo.hackathon_park_mobile.aMain

import moxy.MvpView
import moxy.viewstate.strategy.alias.OneExecution

interface MainView: MvpView {

    @OneExecution
    fun toLoginFragment()

    @OneExecution
    fun toMainMenuFragment()

    @OneExecution
    fun showSnackbar(message: String)
}