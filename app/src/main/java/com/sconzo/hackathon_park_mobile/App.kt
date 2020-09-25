package com.sconzo.hackathon_park_mobile

import android.app.Application
import com.sconzo.hackathon_park_mobile.model.preferences.PreferencesInst

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        PreferencesInst.init(applicationContext)
    }
}