package com.live.operator

import android.util.Log
import com.live.rx.BasicFuseableObserver
import com.live.rx.ObservableSource
import com.live.rx.Observer
import java.util.concurrent.TimeUnit

class ObservableSubscribeOn<T>(
    private val source: ObservableSource<T>,
    private val scheduler: Scheduler
) : AbstractObservableWithUpStream<T, T>(source) {

    override fun subscribeActual(observer: Observer<T>) {
//        println("ObservableSubscribeOn--------subscribeActual---$observer")
        scheduler.scheduleDirect(SubscribeTask(source,SubscribeOnObserver(observer)),0,TimeUnit.NANOSECONDS)
    }

    class SubscribeOnObserver<T>(private val downStream: Observer<T>) : BasicFuseableObserver<T,T>(downStream) {

        override fun onSubscribe() {
        }

        override fun onNext(t: T) {
//            println("SubscribeOnObserver------onNext==t=$t---$downstream")
            downStream.onNext(t)
        }

        override fun onError(e: Throwable) {
            downStream.onError(e)
        }

        override fun onCompleted() {
            downStream.onCompleted()
        }

    }

    class SubscribeTask<T>(private val source: ObservableSource<T>,private val parent: SubscribeOnObserver<T>):Runnable{

        override fun run() {
//            println("SubscribeTask---run")
           source.subscribe(parent)
        }

    }
}