package com.sconzo.hackathon_park_mobile.fMainMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sconzo.hackathon_park_mobile.R
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


class MainMenuFragment : MvpAppCompatFragment(), MainMenuView {

    @InjectPresenter
    lateinit var mainMenuPresenter: MainMenuPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }


}