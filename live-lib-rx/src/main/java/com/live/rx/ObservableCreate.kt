package com.live.rx

class ObservableCreate<T>(private val source:ObservableOnSubscribe<T>):Observable<T>() {


    override fun subscribeActual(observer: Observer<T>) {
        source.subscribe(CreateEmitter(observer))
    }

    class CreateEmitter<T> (private val observer:Observer<T>): ObservableEmitter<T>{
        override fun onNext(t: T) {
            observer.onNext(t)
        }

        override fun onError(e: Throwable) {
            observer.onError(e)
        }

        override fun onCompleted() {
           observer.onCompleted()
        }

    }

}

