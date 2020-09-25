package com.sconzo.hackathon_park_mobile.model.preferences

import android.content.Context

object PreferencesInst {

    private var instance: ModelPreferences? = null

    fun init(context: Context) {
        instance = ModelPreferences(context)
    }

    fun get(): ModelPreferences = instance ?: throw Throwable("ModelPreferences is null")

    fun clearAll() {
        instance?.clear()
        instance = null
    }
}
