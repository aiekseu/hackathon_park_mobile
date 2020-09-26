package com.sconzo.hackathon_park_mobile.fMainMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sconzo.hackathon_park_mobile.R
import kotlinx.android.synthetic.main.fragment_main_menu.*
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainmenu_btn_toEvents.setOnClickListener{
            findNavController().navigate(R.id.action_mainMenuFragment_to_initiativesFragment)
        }
        mainmenu_btn_toNews.setOnClickListener{
            findNavController().navigate(R.id.action_mainMenuFragment_to_newsFragment)
        }
        mainmenu_btn_toSales.setOnClickListener{
            findNavController().navigate(R.id.action_mainMenuFragment_to_promosFragment)
        }
    }


}