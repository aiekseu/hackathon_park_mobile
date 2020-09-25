package com.sconzo.hackathon_park_mobile.fRegister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.sconzo.hackathon_park_mobile.*
import kotlinx.android.synthetic.main.fragment_register_firstpage.*


import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


class RegisterFragment : MvpAppCompatFragment(), RegisterView {

    @InjectPresenter
    lateinit var registerPresenter: RegisterPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_register_firstpage, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        register_btn_continue.setOnClickListener {
            registerPresenter.onRegister(
                email = register_edt_email.text.toString(),
                name = register_edt_name.text.toString(),
                surname = register_edt_surname.text.toString(),
                password = register_edt_password.text.toString(),
                password2 = register_edt_repeatPassword.text.toString()
            )
        }

        register_btn_back.setOnClickListener {
            hideKeyboard()
            findNavController().popBackStack()
        }

        register_edt_name.setErrorListener(register_edt_nameLayout)
        register_edt_email.setErrorListener(register_edt_emailLayout)
        register_edt_password.setErrorListener(register_edt_passwordLayout)
        register_edt_repeatPassword.setErrorListener(register_edt_repeatPasswordLayout)


        register_edt_name.doFocusGone {
            registerPresenter.isNameValid(text.toString())
        }
        register_edt_email.doFocusGone {
            registerPresenter.isEmailValid(text.toString())
        }

        register_edt_password.doFocusGone {
            registerPresenter.isPasswordValid(text.toString())
        }
        register_edt_repeatPassword.doFocusGone {
            registerPresenter.isPassword2Valid(
                register_edt_password.text.toString(),
                text.toString()
            )
        }

//        register_edt_repeatPassword.setOnEditorActionListener { _, actionId, _ ->
//            return@setOnEditorActionListener if (actionId == EditorInfo.IME_ACTION_GO) {
//                hideKeyboard()
//                register_btn_continue.performClick()
//                true
//            } else {
//                false
//            }
//        }


    }


    // Data Errors Show

    override fun setNameError(message: String) {
        register_edt_nameLayout.error = message
    }

    override fun setEmailError(message: String) {
        register_edt_emailLayout.error = message
    }


    override fun setPasswordError(message: String) {
        register_edt_passwordLayout.error = message
    }

    override fun setRepeatPasswordError(message: String) {
        register_edt_repeatPasswordLayout.error = message
    }


    // Registration Finish

    override fun finishRegisterProcess() {
        showSnackbar("Регистрация завершена")
        //findNavController().navigate(R.id.action_registerFragment_to_cartFragment)
    }

    override fun navigateFurther() {
        findNavController().navigate(R.id.action_registerFragment_to_registerTwoFragment)
    }


    // User Inform

    override fun showSnackbar(message: String) {
        view?.showSnackbar(message)
    }

    override fun showProgressBar(isVisible: Boolean) {
        if (isVisible) {
            //register_btn_continue.startProgressCenter()
        } else {
            //register_btn_continue.doneProgress()
        }
    }

}