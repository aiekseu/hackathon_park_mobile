package com.sconzo.hackathon_park_mobile.model.data

import com.google.gson.annotations.Expose

data class User(
    val id_user: String = "",
    @Expose(deserialize = false) var email: String = "",
    val name: String = "",
    val region: String = "",
    val city: String = "",
    @Expose(deserialize = false) var password: String = ""
)