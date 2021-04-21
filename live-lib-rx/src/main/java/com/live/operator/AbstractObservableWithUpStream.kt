package com.live.operator

import com.live.rx.Observable
import com.live.rx.ObservableSource

abstract class AbstractObservableWithUpStream<T,U>(private val source:ObservableSource<T>): Observable<U>() {
}