package com.live.rx

interface ObservableSource<T> {
    fun subscribe(observer: Observer<T>)
}