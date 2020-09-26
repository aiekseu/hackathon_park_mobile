package com.sconzo.hackathon_park_mobile.aMain

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        internetListener = { isInternetAvailable() }

       bottom_navigation.setOnNavigationItemSelectedListener { item ->
           when (item.itemId) {
               R.id.page_news -> findNavController(R.id.main_navigation).navigate(R.id.action_global_mainMenuFragment)
               R.id.page_map -> findNavController(R.id.main_navigation).navigate(R.id.action_global_mapFragment)
               R.id.page_buildings -> findNavController(R.id.main_navigation).navigate(R.id.action_global_buyFragment)
               R.id.page_profile -> findNavController(R.id.main_navigation).navigate(R.id.action_global_profileFragment)
           }
           true
       }

    }


    // Navigation Move
    override fun toLoginFragment() {
        bottom_navigation.visibility = View.GONE
        findNavController(R.id.main_navigation).navigate(R.id.action_global_loginFragment)
    }

    override fun toMainMenuFragment() {
        bottom_navigation.visibility = View.VISIBLE
        findNavController(R.id.main_navigation).navigate(R.id.action_global_mainMenuFragment)
    }



    // User Notify
    override fun showSnackbar(message: String) {
        main_navigation.showSnackbar(message)
    }



    override fun onResume() {
        super.onResume()

        // TODO: Удалить при подключении сервера
        mainPresenter.toLogin()
    }


    companion object {
        var internetListener: () -> Boolean = { false }

        fun isInternetActive(): Boolean = internetListener()
    }
}