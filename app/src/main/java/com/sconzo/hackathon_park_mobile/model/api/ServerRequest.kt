package com.sconzo.hackathon_park_mobile.model.api

import com.sconzo.hackathon_park_mobile.model.data.PaymentCart
import com.sconzo.hackathon_park_mobile.model.data.Product
import com.sconzo.hackathon_park_mobile.model.data.User


data class ServerRequest<T>(
    val v: String = VERSION_API,
    val method: String = "",
    val params: T
) {
    companion object {

        // API Version

        const val VERSION_API = "2.0"

        // Payment Methods

        private const val METHOD_GO_PAYMENT = "goPayment"

        fun goPayment(paymentCart: PaymentCart): ServerRequest<PaymentCart> {
            return ServerRequest(method = METHOD_GO_PAYMENT, params = paymentCart)
        }


        // Products Methods
        private const val METHOD_GET_PRODUCT = "getProduct"
        private const val METHOD_PAY_PRODUCTS = "setPaidProducts"
        private const val METHOD_CHECK_PAID = "checkPaidProducts"

        fun checkPaidProducts(listProducts: ArrayList<Product>): ServerRequest<ArrayList<Product>> {
            return ServerRequest(method = METHOD_CHECK_PAID, params = listProducts)
        }

        fun getProduct(product: Product): ServerRequest<Product> {
            return ServerRequest(method = METHOD_GET_PRODUCT, params = product)
        }

        fun payProducts(listProducts: ArrayList<Product>): ServerRequest<ArrayList<Product>> {
            return ServerRequest(method = METHOD_PAY_PRODUCTS, params = listProducts)
        }


        // Login and Register Methods
        private const val METHOD_LOGIN = "login"
        private const val METHOD_REGISTER = "register"

        fun loginUser(user: User): ServerRequest<User> {
            return ServerRequest(method = METHOD_LOGIN, params = user)
        }

        fun registerUser(user: User): ServerRequest<User> {
            return ServerRequest(method = METHOD_REGISTER, params = user)
        }
    }
}
