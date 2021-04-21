package com.live.rx

interface Emitter<T> {
    fun onNext(t:T)
    fun onError(e:Throwable)
    fun onCompleted()
}