package com.sconzo.hackathon_park_mobile.aMain

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.sconzo.hackathon_park_mobile.R
import com.sconzo.hackathon_park_mobile.isInternetAvailable
import com.sconzo.hackathon_park_mobile.showSnackbar
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), MainView {
    @InjectPresenter
    lateinit var mainPresenter: MainPresenter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        internetListener = { isInternetAvailable() }
    }

    override fun onResume() {
        super.onResume()
        mainPresenter.toLogin()
    }



    // Cart/Login Fragments Move

//    override fun toCartFragment() {
//        findNavController(R.id.main_navigation).navigate(R.id.action_global_cartFragment)
//    }

    override fun toLoginFragment() {
        findNavController(R.id.main_navigation).navigate(R.id.action_global_loginFragment)
    }


    // User Notify

    override fun showSnackbar(message: String) {
        main_navigation.showSnackbar(message)
    }




    companion object {

        // TODO: 31.08.2020 Костыль с интернет проверкой

        var internetListener: () -> Boolean = { false }

        fun isInternetActive(): Boolean = internetListener()
    }
}