package com.example.jrhapplication

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData

class TestLiveData {

    fun a(){
        val liveData = MutableLiveData<Int>()

        liveData.postValue(1)
        liveData.value = 2

        liveData.observeForever{
            println("-------$it")
        }

    }
}

fun main() {
    TestLiveData().a()
}