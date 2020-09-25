package com.sconzo.hackathon_park_mobile.model.data

import com.google.gson.annotations.Expose

data class User(
    var name: String = "",
    var surname: String = "",
    @Expose(deserialize = false) var email: String = "",
    @Expose(deserialize = false) var password: String = "",
    var birthday: String = "",
    var sex: String = "",
    var height: Double = 0.0,
    var hasKids: Boolean = false,
    var isMarried: Boolean = false
)