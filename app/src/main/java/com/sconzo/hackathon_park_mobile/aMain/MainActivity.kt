package com.sconzo.hackathon_park_mobile.aMain

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sconzo.hackathon_park_mobile.R
import com.sconzo.hackathon_park_mobile.isInternetAvailable
import com.sconzo.hackathon_park_mobile.showSnackbar
import com.sconzo.hackathon_park_mobile.showToast
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), MainView {
    @InjectPresenter
    lateinit var mainPresenter: MainPresenter


    lateinit var bottomMenu: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomMenu = bottom_navigation

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
        bottom_navigation.visibility = View.GONE
        findNavController(R.id.main_navigation).navigate(R.id.action_global_loginFragment)
    }


    // User Notify

    override fun showSnackbar(message: String) {
        main_navigation.showSnackbar(message)
    }





    companion object {
        var internetListener: () -> Boolean = { false }

        fun isInternetActive(): Boolean = internetListener()
    }
}