package com.live.operator

import com.live.functions.Function
import com.live.rx.BasicFuseableObserver
import com.live.rx.ObservableSource
import com.live.rx.Observer

class ObservableMap<T, U>(
    private val source: ObservableSource<T>,
    private val func: Function<T, U>
) : AbstractObservableWithUpStream<T, U>(source) {

    override fun subscribeActual(observer: Observer<U>) {
        source.subscribe(MapObserver(observer,func))
    }


    class MapObserver<T, U>(private val downstream: Observer<U>,private val mapper:Function<T, U>) :
        BasicFuseableObserver<T, U>(downstream) {
        override fun onSubscribe() {

        }

        override fun onNext(t: T) {
            val u = mapper.apply(t)
            downstream.onNext(u)
        }

        override fun onError(e: Throwable) {
            downstream.onError(e)
        }

        override fun onCompleted() {
            downstream.onCompleted()
        }

    }




}