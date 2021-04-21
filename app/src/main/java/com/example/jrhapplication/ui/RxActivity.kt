package com.example.jrhapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jrhapplication.R
import com.live.functions.Function
import com.live.operator.MainThreadScheduler
import com.live.operator.NewThreadScheduler
import com.live.operator.RxThreadFactory
import com.live.rx.*
import kotlinx.android.synthetic.main.activity_rx.*

class RxActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx)
        rxBtn.setOnClickListener {
            testNewThreadMap()
        }
    }
}


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