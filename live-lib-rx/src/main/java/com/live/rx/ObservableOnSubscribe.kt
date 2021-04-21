package com.live.rx

interface ObservableOnSubscribe<T> {
    fun subscribe(emitter:ObservableEmitter<T>)
}