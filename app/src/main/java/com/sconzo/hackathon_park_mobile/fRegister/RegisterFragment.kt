package com.sconzo.hackathon_park_mobile.fRegister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sconzo.hackathon_park_mobile.R

import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


class RegisterFragment : MvpAppCompatFragment(), RegisterView {

    @InjectPresenter
    lateinit var registerPresenter: RegisterPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setNameError(message: String) {
        TODO("Not yet implemented")
    }

    override fun setEmailError(message: String) {
        TODO("Not yet implemented")
    }

    override fun setRegionError(message: String) {
        TODO("Not yet implemented")
    }

    override fun setCityError(message: String) {
        TODO("Not yet implemented")
    }

    override fun setPasswordError(message: String) {
        TODO("Not yet implemented")
    }

    override fun setRepeatPasswordError(message: String) {
        TODO("Not yet implemented")
    }

    override fun showSnackbar(message: String) {
        TODO("Not yet implemented")
    }

    override fun showProgressBar(isVisible: Boolean) {
        TODO("Not yet implemented")
    }

    override fun finishRegisterProcess() {
        TODO("Not yet implemented")
    }

}