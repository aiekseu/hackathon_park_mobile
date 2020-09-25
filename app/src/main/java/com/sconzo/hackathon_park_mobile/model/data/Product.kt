package com.sconzo.hackathon_park_mobile.model.data

import java.math.BigDecimal

data class Product(
    val id: String,
    val id_item: String = "",
    val name: String = "",
    val description: String = "",
    val url_image: String = "",
    val price: BigDecimal = BigDecimal.valueOf(0.00),
    val is_paid: Boolean = false
)