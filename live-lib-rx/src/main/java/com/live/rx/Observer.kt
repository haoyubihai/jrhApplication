package com.live.rx

interface Observer<T> {
    fun onSubscribe()
    fun onNext(t: T)
    fun onError(e: Throwable)
    fun onCompleted()
}