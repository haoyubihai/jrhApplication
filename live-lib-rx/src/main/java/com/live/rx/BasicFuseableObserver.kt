package com.live.rx

abstract class BasicFuseableObserver<T,U>(private val downstream:Observer<U>) :Observer<T>{
}