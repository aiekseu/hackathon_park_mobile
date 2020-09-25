package com.sconzo.hackathon_park_mobile.model

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

object RxBus {

    private val publisher = PublishSubject.create<Any>()

    fun publish(event: Any) {
        publisher.onNext(event)
    }

    fun <T> listen(eventType: Class<T>): Observable<T> =
        publisher
            .ofType(eventType)
            .subscribeOn(Schedulers.computation())
}