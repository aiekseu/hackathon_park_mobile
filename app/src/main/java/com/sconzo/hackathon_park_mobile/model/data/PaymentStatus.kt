package com.sconzo.hackathon_park_mobile.model.data

//import com.sconzo.hackathon_park_mobile.Errors

data class PaymentStatus(
    val status: String = CANCELED,
    val confirmation_url: String = "",
    //val cancel_reason: String = Errors.ERROR_SERVER
) {
    companion object {
        const val PENDING = "pending"
        const val WAITING_FOR_CAPTURE = "waiting_for_capture"
        const val SUCCEEDED = "succeeded"
        const val CANCELED = "canceled"
    }
}