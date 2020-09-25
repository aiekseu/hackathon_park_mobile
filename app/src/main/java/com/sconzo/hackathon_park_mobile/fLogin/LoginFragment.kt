package com.sconzo.hackathon_park_mobile.fLogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sconzo.hackathon_park_mobile.R


import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


class LoginFragment : MvpAppCompatFragment(), LoginView {

    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun toRegisterFragment() {
        TODO("Not yet implemented")
    }

    override fun finishLoginFragment() {
        TODO("Not yet implemented")
    }

    override fun showPasswordError(isError: Boolean) {
        TODO("Not yet implemented")
    }

    override fun showSnackbar(message: String) {
        TODO("Not yet implemented")
    }

    override fun showProgressBar(isVisible: Boolean) {
        TODO("Not yet implemented")
    }


}