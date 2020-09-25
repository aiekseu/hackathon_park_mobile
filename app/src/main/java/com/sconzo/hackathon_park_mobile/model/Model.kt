package com.sconzo.hackathon_park_mobile.model

import androidx.core.text.isDigitsOnly
import com.sconzo.hackathon_park_mobile.defaultModelObservable
import com.sconzo.hackathon_park_mobile.model.api.Api
import com.sconzo.hackathon_park_mobile.model.api.ServerRequest
import com.sconzo.hackathon_park_mobile.model.api.ServerResponse
import com.sconzo.hackathon_park_mobile.model.data.*
import com.sconzo.hackathon_park_mobile.model.preferences.PreferencesInst
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class Model {

    private val api = Api.getService()
    private val pref = PreferencesInst.get()


    // Login and Register Methods

    fun loginUser(serverRequest: ServerRequest<User>): Observable<ServerResponse<User>> =
        api.loginUser(serverRequest)
            .defaultModelObservable()

    fun registerUser(serverRequest: ServerRequest<User>): Observable<ServerResponse<Boolean>> =
        api.registerUser(serverRequest)
            .defaultModelObservable()


    // Payment Methods

    fun goPayment(serverRequest: ServerRequest<PaymentCart>): Observable<ServerResponse<PaymentStatus>> =
        api.goPayment(serverRequest)
            .defaultModelObservable()


    // Products Methods

    fun checkPaidProducts(serverRequest: ServerRequest<ArrayList<Product>>): Observable<ServerResponse<ProductPaymentVerdict>> =
        api.checkPaidProducts(serverRequest)
            .defaultModelObservable()

    fun getProduct(serverRequest: ServerRequest<Product>): Observable<ServerResponse<Product>> =
        api.getProduct(serverRequest)
            .defaultModelObservable()

    fun payProducts(serverRequest: ServerRequest<ArrayList<Product>>): Observable<ServerResponse<Boolean>> =
        api.payProducts(serverRequest)
            .defaultModelObservable()


    // Other Methods

    fun publishRxProductId(productId: String) = RxBus.publish(productId)

    fun listenRxProductId(): Observable<String> = RxBus.listen(String::class.java)
        .observeOn(Schedulers.computation())
        .map { it.trim() }
        .filter { it.isNotEmpty() && it.isDigitsOnly() }

    fun getPreferences() = pref
}