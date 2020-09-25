package com.sconzo.hackathon_park_mobile.model.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Api {

//    const val BASE_URL = "http://192.168.1.161/index.php/"
const val BASE_URL = "http://outofturn.xyz/index.php/"

    private lateinit var service: IApi
    private val client = OkHttpClient()
    private val gsonFactory = GsonConverterFactory.create()
    private val rxJavaAdapter = RxJava2CallAdapterFactory.create()

    fun getService(): IApi {
        return if (this::service.isInitialized) {
            service
        } else {
            val retrofit = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(rxJavaAdapter)
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonFactory)
                .build()
            service = retrofit.create(IApi::class.java)
            service
        }
    }
}