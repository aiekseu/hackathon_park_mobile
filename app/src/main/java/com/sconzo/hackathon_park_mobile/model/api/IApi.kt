package com.sconzo.hackathon_park_mobile.model.api

import com.sconzo.hackathon_park_mobile.model.data.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface IApi {

    // Login and Register Methods

    @POST(Api.BASE_URL)
    fun loginUser(@Body request: ServerRequest<User>)
            : Observable<ServerResponse<User>>

    @POST(Api.BASE_URL)
    fun registerUser(@Body request: ServerRequest<User>)
            : Observable<ServerResponse<Boolean>>


    // Products Methods

    @POST(Api.BASE_URL)
    fun checkPaidProducts(@Body request: ServerRequest<ArrayList<Product>>)
            : Observable<ServerResponse<ProductPaymentVerdict>>

    @POST(Api.BASE_URL)
    fun getProduct(@Body request: ServerRequest<Product>)
            : Observable<ServerResponse<Product>>

    @POST(Api.BASE_URL)
    fun payProducts(@Body request: ServerRequest<ArrayList<Product>>)
            : Observable<ServerResponse<Boolean>>


    // Payment Methods

    @POST(Api.BASE_URL)
    fun goPayment(@Body request: ServerRequest<PaymentCart>)
            : Observable<ServerResponse<PaymentStatus>>
}