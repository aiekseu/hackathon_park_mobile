package com.sconzo.hackathon_park_mobile.fRegister3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sconzo.hackathon_park_mobile.R
import com.sconzo.hackathon_park_mobile.hideKeyboard
import kotlinx.android.synthetic.main.fragment_register_firstpage.*
import kotlinx.android.synthetic.main.fragment_register_secondpage.*
import kotlinx.android.synthetic.main.fragment_register_secondpage.register2_btn_continue
import kotlinx.android.synthetic.main.fragment_register_thirdpage.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


class RegisterThreeFragment : MvpAppCompatFragment(), RegisterThreeView {

    @InjectPresenter
    lateinit var registerThreePresenter: RegisterThreePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_thirdpage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        register3_btn_back.setOnClickListener {
            hideKeyboard()
            findNavController().popBackStack()
        }
        register3_btn_continue.setOnClickListener {
            findNavController().navigate(R.id.action_registerThreeFragment_to_loginFragment)
        }
    }

}