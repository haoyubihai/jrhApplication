package com.live.rx

import com.live.functions.Function
import com.live.operator.MainThreadScheduler
import com.live.operator.NewThreadScheduler
import com.live.operator.RxThreadFactory
import org.junit.Test

class RxTest {
    @Test
    fun testCreate() {
        Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) {
                emitter.onNext("hello")
            }

        }).subscribe(object : Observer<String> {
            override fun onSubscribe() {

            }

            override fun onNext(t: String) {
                println("hello--onnext--$t")
            }

            override fun onError(e: Throwable) {
            }

            override fun onCompleted() {
            }
        })
    }


    @Test
    fun testMap() {
        Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) {
                emitter.onNext("hello")
            }

        }).map(object : Function<String, String> {
            override fun apply(t: String): String {
                return "$t-----map--------"
            }

        }).subscribe(object : Observer<String> {
            override fun onSubscribe() {

            }

            override fun onNext(t: String) {
                println("$t--hello--onnext--")
            }

            override fun onError(e: Throwable) {
            }

            override fun onCompleted() {
            }
        })
    }


    @Test
    fun testNewThreadMap() {
        Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) {
                emitter.onNext("before--thread=${Thread.currentThread().name}")
            }

        }).map(object : Function<String, String> {
                override fun apply(t: String): String {
                    return "$t----map--------"
                }

            })
            .subscribeOn(NewThreadScheduler(RxThreadFactory()))
            .observerOn(MainThreadScheduler())
            .subscribe(object : Observer<String> {
                override fun onSubscribe() {

                }

                override fun onNext(t: String) {
                    println("$t------result-------222222thread=${Thread.currentThread().name}")
                }

                override fun onError(e: Throwable) {
                }

                override fun onCompleted() {
                }
            })
    }
}
