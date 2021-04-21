package com.live.rx

import com.live.functions.Function
import com.live.operator.ObservableMap
import com.live.operator.ObservableSubscribeOn
import com.live.operator.ObserverOn
import com.live.operator.Scheduler

abstract class Observable<T> :ObservableSource<T> {
    override fun subscribe(observer: Observer<T>) {
        subscribeActual(observer)
    }

    protected abstract fun subscribeActual(observer: Observer<T>)

    companion object{
        fun <T> create(source: ObservableOnSubscribe<T>): Observable<T> {
            return ObservableCreate(source)
        }
    }
}

fun <T,U> Observable<T>.map(mapper:Function<T, U>):Observable<U>{
    return ObservableMap(this,mapper)
}

fun <T> Observable<T>.subscribeOn(scheduler: Scheduler):Observable<T>{
    return ObservableSubscribeOn(this,scheduler)
}

fun <T> Observable<T>.observerOn(scheduler: Scheduler):Observable<T>{
    return ObserverOn(this,scheduler)
}
