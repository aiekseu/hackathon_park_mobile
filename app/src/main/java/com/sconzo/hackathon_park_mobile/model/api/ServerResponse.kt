package com.sconzo.hackathon_park_mobile.model.api

import com.sconzo.hackathon_park_mobile.model.data.ErrorRPC

data class ServerResponse<T>(
    val result: T? = null,
    val error: ErrorRPC? = null
)