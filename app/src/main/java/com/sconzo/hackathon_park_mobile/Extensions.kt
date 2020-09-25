package com.sconzo.hackathon_park_mobile

import android.nfc.NdefRecord
import android.util.Log
import com.google.gson.Gson
import com.sconzo.hackathon_park_mobile.aMain.MainActivity
import com.sconzo.hackathon_park_mobile.model.api.ServerResponse
import com.sconzo.hackathon_park_mobile.model.data.ErrorRPC
import com.sconzo.hackathon_park_mobile.model.data.Product
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.UnsupportedEncodingException
import java.math.BigDecimal
import java.math.RoundingMode
import java.nio.charset.Charset
import kotlin.experimental.and


const val TAG_DEBUG = "outOfTurn_tag_debug"


// Log Show

fun makeLogDebug(message: String) {
    Log.d(TAG_DEBUG, message)
}

fun makeLogError(message: String) {
    Log.e(TAG_DEBUG, message)
}

fun throwError(throwable: Throwable) {
    makeLogError(throwable.toString())
}

fun makeJsonLogDebug(obj: Any) {
    makeLogDebug(Gson().toJson(obj))
}


// Text From Payload Return

fun NdefRecord.readText(): String {
    val payload = payload
    val textEncoding =
        if (payload[0].toInt() and 128 == 0) "UTF-8" else "UTF-16"
    val languageCodeLength = (payload[0] and 51).toInt()
    return try {
        val text = String(
            payload,
            languageCodeLength + 1,
            payload.size - languageCodeLength - 1,
            Charset.forName(textEncoding)
        )
        makeLogDebug("NFC with data: $text")
        text
    } catch (e: UnsupportedEncodingException) {
        Log.e("UnsupportedEncoding", e.toString())
        "UnsupportedEncoding"
    }
}


// By Default Observe

fun <T> Observable<ServerResponse<T>>.defaultModelObservable(): Observable<ServerResponse<T>> =
    this.subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnError {
            makeLogError(it.toString())
        }
        .onErrorReturn {
            if (MainActivity.isInternetActive()) {
                makeLogDebug("ON")
                ServerResponse(error = ErrorRPC(Errors.CODE_DEFAULT, Errors.ERROR_DEFAULT))
            } else {
                makeLogDebug("OFF")
                ServerResponse(error = ErrorRPC(Errors.CODE_DEFAULT, Errors.ERROR_INTERNET))
            }
//            ServerResponse(error = ErrorRPC(Errors.CODE_DEFAULT, Errors.ERROR_DEFAULT))
        }


// List Product Collector

//fun ArrayList<Product>.toListProductCollector(): List<ProductCollector> {
//    return map {
//        ProductCollector(it)
//    }
//}


fun BigDecimal.round(decimals: Int = 2): BigDecimal = setScale(decimals, RoundingMode.FLOOR)

fun ArrayList<Product>.containsId(productId: String): Int {
    forEachIndexed { index, product ->
        if (product.id == productId) {
            return index
        }
    }
    return -1
}
