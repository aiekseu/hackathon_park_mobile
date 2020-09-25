package com.sconzo.hackathon_park_mobile.fRegister

import android.text.TextUtils
import com.sconzo.hackathon_park_mobile.Errors
import com.sconzo.hackathon_park_mobile.PatternsRegExp
import com.sconzo.hackathon_park_mobile.makeLogError
import com.sconzo.hackathon_park_mobile.model.Model
import com.sconzo.hackathon_park_mobile.model.api.ServerRequest
import com.sconzo.hackathon_park_mobile.model.data.User
import com.sconzo.hackathon_park_mobile.model.preferences.ModelPreferences
import com.sconzo.hackathon_park_mobile.throwError
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class RegisterPresenter: MvpPresenter<RegisterView>() {
    private val disposables = CompositeDisposable()
    val model = Model()


    // Registration Start

    fun onRegister(
        email: String,
//        region: String,
//        defaultRegions: Array<String>,
//        city: String,
        name: String,
        password: String,
        password2: String
    ) {
        if (isDataValid(
                email = email,
//                region = region,
//                defaultRegions = defaultRegions,
//                city = city,
                name = name,
                password = password,
                password2 = password2
            )
        ) {
            inciteProgressBar(true)
            val user =
                User(email = email, name = name, password = password)
            val disposableRegisterUser = model.registerUser(ServerRequest.registerUser(user))
                .subscribe(
                    { response ->
                        inciteProgressBar(false)
                        val pref = model.getPreferences()
                        if (response.result == null || response.result == false) {
                            makeLogError(response.error?.message ?: Errors.ERROR_SERVER)
                            viewState.showSnackbar(response.error?.message ?: Errors.ERROR_SERVER)
                        } else if (pref == null) {
                            makeLogError(Errors.ERROR_APP)
                            viewState.showSnackbar(Errors.ERROR_APP)
                        } else {
                            finishRegisterSuccess(pref, user)
                        }
                    },
                    {
                        throwError(it)
                        viewState.showSnackbar(Errors.ERROR_APP)
                    })
            disposables.add(disposableRegisterUser)
        }
    }


    // Registration Finish

    private fun finishRegisterSuccess(pref: ModelPreferences, user: User) {
        pref.setIsLoggedIn(true)
        pref.setUser(user)
        viewState.finishRegisterProcess()
    }


    // Data Validate

    private fun isDataValid(
        email: String,

        name: String,
        password: String,
        password2: String
    ): Boolean {

        // Email Validation
        val isEmailValid = isEmailValid(email)

        // Name Validation
        val isNameValid = isNameValid(name)

        // Region Validation
        //val isRegionValid = isRegionValid(region, defaultRegions)
        val isRegionValid = true

        // City Validation
        //val isCityValid = isCityValid(city)
        val isCityValid = true

        // Password Validation
        val isPasswordValid = isPasswordValid(password)

        // Password2 Validation
        val isRepeatPasswordValid = isPassword2Valid(password, password2)

        return isEmailValid && isNameValid && isRegionValid && isCityValid && isPasswordValid && isRepeatPasswordValid
    }


    // EditText Contents Check

    fun isNameValid(name: String): Boolean {
        when {
            TextUtils.isEmpty(name) -> viewState.setNameError("Введите имя")
            !isName(name) -> viewState.setNameError("Неверное имя")
            else -> {
                viewState.setNameError("")
                return true
            }
        }
        return false
    }

    fun isEmailValid(email: String): Boolean {
        when {
            TextUtils.isEmpty(email) -> viewState.setEmailError("Введите Email")
            !isEmail(email) -> viewState.setEmailError("Неправильный Email")
            else -> {
                viewState.setEmailError("")
                return true
            }
        }
        return false
    }

    fun isRegionValid(region: String, defaultRegions: Array<String>): Boolean {
        when {
            TextUtils.isEmpty(region) -> viewState.setRegionError("Выберите регион")
            !defaultRegions.contains(region) -> viewState.setRegionError("Выберите регион из списка")
            else -> {
                viewState.setRegionError("")
                return true
            }
        }
        return false
    }

    fun isCityValid(city: String): Boolean {
        when {
            TextUtils.isEmpty(city) -> viewState.setCityError("Введите город")
            else -> {
                viewState.setCityError("")
                return true
            }
        }
        return false

    }

    fun isPasswordValid(password: String): Boolean {
        when {
            TextUtils.isEmpty(password) -> viewState.setPasswordError("Введите пароль")
            password.length < 6 -> viewState.setPasswordError("Пароль должен быть больше 5 символов")
            password.length > 20 -> viewState.setPasswordError("Пароль должен быть меньше 21 символа")
            !isPassword(password) -> viewState.setPasswordError("Пароль должен содержать одну цифру, одну букву и любой из @#\\$% символ")
            else -> {
                viewState.setPasswordError("")
                return true
            }
        }
        return false
    }

    fun isPassword2Valid(password: String, password2: String): Boolean {
        when {
            TextUtils.isEmpty(password2) -> viewState.setRepeatPasswordError("Введите пароль")
            password != password2 -> viewState.setRepeatPasswordError("Пароль не совпадает")
            else -> {
                viewState.setRepeatPasswordError("")
                return true
            }
        }
        return false
    }


    // Regex Pattern Validate

    fun isName(string: String): Boolean {
        val nameRegex = PatternsRegExp.nameRegex
        return nameRegex.matches(string)
    }

    fun isEmail(string: String): Boolean {
        val emailRegex = PatternsRegExp.emailRegex
        return emailRegex.matches(string)
    }

    fun isPassword(string: String): Boolean {
        val passRegex = PatternsRegExp.passRegex
        return passRegex.matches(string)
    }


    // User Inform

    private fun inciteProgressBar(isVisible: Boolean) {
        viewState.showProgressBar(isVisible)
    }


    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}