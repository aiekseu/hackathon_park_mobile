package com.sconzo.hackathon_park_mobile.model.data

import java.math.BigDecimal

data class PaymentCart(
    val payment_token: String,
    val sum: BigDecimal,
    val description: String
)