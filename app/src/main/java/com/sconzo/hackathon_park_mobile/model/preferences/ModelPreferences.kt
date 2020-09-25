package com.sconzo.hackathon_park_mobile.model.preferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.sconzo.hackathon_park_mobile.model.data.User

class ModelPreferences(context: Context) {

    private var pref: SharedPreferences =
        context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE)

    fun clear() {
        pref.edit()
            .clear()
            .apply()
    }

    fun setIsLoggedIn(b: Boolean) {
        pref.edit()
            .putBoolean(PREF_isLoggedIn, b)
            .apply()
    }

    fun getIsLoggedIn() = pref.getBoolean(PREF_isLoggedIn, false)

    fun setUser(user: User) {
        val userJson: String = Gson().toJson(user)
        pref.edit()
            .putString(PREF_userJson, userJson)
            .apply()
    }

    fun getUser(): User {
        val userJson: String? = pref.getString(PREF_userJson, null)
        return Gson().fromJson(userJson, User::class.java)
    }

    companion object {
        const val PREF_APP = "outOfTurn_preferences"
        const val PREF_isLoggedIn = "preferences_isLoggedIn"
        const val PREF_userJson = "preferences_userJson"
    }
}