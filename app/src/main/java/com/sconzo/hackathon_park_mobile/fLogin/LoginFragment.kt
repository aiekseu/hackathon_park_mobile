package com.sconzo.hackathon_park_mobile.fLogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sconzo.hackathon_park_mobile.*
import kotlinx.android.synthetic.main.fragment_login.*


import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


class LoginFragment : MvpAppCompatFragment(), LoginView {

    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: при подключении сервера убрать знак комментария
        //login_btn_enter.isEnabled = false

        login_btn_enter.setOnClickListener {
            val email = login_edt_email.text.toString()
            val password = login_edt_password.text.toString()

            // TODO: при подключении сервера убрать знак комментария
            //loginPresenter.loginUser(email, password)
            finishLoginFragment()
        }

        login_btn_toRegister.setOnClickListener {
            loginPresenter.toRegisterFragment()
        }

        login_edt_email.setErrorListener(login_edt_emailLayout)
        login_edt_password.setErrorListener(login_edt_passwordLayout)

        login_edt_email.doAfterTextChanged {
            login_btn_enter.isEnabled =
                !login_edt_email.text.isNullOrBlank() && !login_edt_password.text.isNullOrBlank()
        }
        login_edt_password.doAfterTextChanged {
            login_btn_enter.isEnabled =
                !login_edt_email.text.isNullOrBlank() && !login_edt_password.text.isNullOrBlank()
        }

        login_edt_password.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener if (actionId == EditorInfo.IME_ACTION_GO) {
                hideKeyboard()
                if (login_btn_enter.isEnabled) {
                    login_btn_enter.performClick()
                }
                true
            } else {
                false
            }
        }

    }


    // Register Fragment Start

    override fun toRegisterFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }


    // Successfully Log In

    override fun finishLoginFragment() {
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.VISIBLE
        findNavController().navigate(R.id.action_global_mainMenuFragment)
    }


    // Incorrect Login or Password Show

    override fun showPasswordError(isError: Boolean) {
        if (isError) {
            login_edt_emailLayout.error = " "
            login_edt_passwordLayout.error = "Неверный логин или пароль"
        } else {
            login_edt_emailLayout.error = ""
            login_edt_passwordLayout.error = ""
        }
    }


    // User Inform

    override fun showProgressBar(isVisible: Boolean) {
        login_btn_toRegister.isClickable = !isVisible
        if (isVisible) {
            //login_btn_enter.startProgressCenter()
        } else {
            //login_btn_enter.doneProgress()
        }
    }

    override fun showSnackbar(message: String) {
        view.showSnackbar(message)
    }


}