package com.sconzo.hackathon_park_mobile.model.data

data class ProductPaymentVerdict(
    val verdict: Boolean = false,
    val listProducts: ArrayList<ProductStatus>
) {
    data class ProductStatus(
        val id: String,
        val status: Int
    )
}