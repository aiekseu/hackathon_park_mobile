package com.sconzo.hackathon_park_mobile.model.data

import com.google.gson.annotations.Expose

data class User(
    val name: String = "",
    val lastName: String = "",
    @Expose(deserialize = false) var email: String = "",
    @Expose(deserialize = false) var password: String = "",
    val birthday: String = "",
    val sex: String = "",
    val height: Double = 0.0,
    val childrenNum: Int = 0,
    val isMarried: Boolean = false
)