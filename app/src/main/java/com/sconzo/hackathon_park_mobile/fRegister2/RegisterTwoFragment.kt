package com.sconzo.hackathon_park_mobile.fRegister2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sconzo.hackathon_park_mobile.R
import com.sconzo.hackathon_park_mobile.hideKeyboard
import com.sconzo.hackathon_park_mobile.showSnackbar
import kotlinx.android.synthetic.main.fragment_register_firstpage.*
import kotlinx.android.synthetic.main.fragment_register_secondpage.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


class RegisterTwoFragment : MvpAppCompatFragment(), RegisterTwoView {

    @InjectPresenter
    lateinit var registerTwoPresenter: RegisterTwoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_secondpage, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        register2_btn_back.setOnClickListener {
            hideKeyboard()
            findNavController().popBackStack()
        }
        register2_btn_continue.setOnClickListener {
            findNavController().navigate(R.id.action_registerTwoFragment_to_loginFragment)
        }

        register2_btn_continue.setOnClickListener {
            var birthday = register2_edt_birthday.text.toString()
            var hasKids = register2_rb_hasKids.isChecked
            var sex = ""
            when {
                register2_rb_sexMale.isChecked -> sex = "male"
                register2_rb_sexFemale.isChecked -> sex = "female"
                register2_rb_sexOther.isChecked -> sex = ""
            }

            registerTwoPresenter.onRegister(birthday, hasKids, sex)
        }
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

    override fun finishRegisterProcess() {
        showSnackbar("Регистрация завершена")
    }


}