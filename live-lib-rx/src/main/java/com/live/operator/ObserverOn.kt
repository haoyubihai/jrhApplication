package com.live.operator

import com.live.rx.BasicFuseableObserver
import com.live.rx.ObservableSource
import com.live.rx.Observer
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.TimeUnit

class ObserverOn<T>(private val source: ObservableSource<T>, private val scheduler: Scheduler) :AbstractObservableWithUpStream<T,T>(source){
    override fun subscribeActual(observer: Observer<T>) {
        source.subscribe(ObserverOnObserver(observer,scheduler))
    }

    class ObserverOnObserver<T>(private val downStream: Observer<T>,private val scheduler:Scheduler):
        BasicFuseableObserver<T, T>(downStream),Runnable{


        private val  datas = LinkedBlockingDeque<T>()

        override fun onSubscribe() {
        }

        override fun onNext(t: T) {
            datas.push(t)
            scheduler.scheduleDirect(this,0,TimeUnit.NANOSECONDS)
        }

        override fun onError(e: Throwable) {
        }

        override fun onCompleted() {
        }


        override fun run() {
            downStream.onNext(datas.pop())
        }

    }
}