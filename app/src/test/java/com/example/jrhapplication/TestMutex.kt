package com.example.jrhapplication

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * kotlin mutex 互斥锁 并发
 * 解决多协程同时抢占同一资源问题
 * @property counterNoMutex Int
 * @property counterWithMutex Int
 * @property mutex Mutex
 * @property j1 Job
 * @property j2 Job
 * @property j3 Job
 * @property j4 Job
 */
class TestMutex {

    var counterNoMutex: Int = 0
    var counterWithMutex: Int = 0
    val mutex = Mutex()

    suspend fun incrementNoMutex() {
        for (i in 0 until 10) {
            counterNoMutex++
        }
    }

    suspend fun incrementWithMutex() {
        mutex.withLock {
            for (i in 0 until 10) {
                counterWithMutex++
            }
        }
    }


    val j1 = CoroutineScope(Dispatchers.IO).launch {
        for (i in 0 until 500) {
            incrementNoMutex()
        }
    }

    val j2 = CoroutineScope(Dispatchers.IO).launch {
        for (i in 0 until 500) {
            incrementNoMutex()
        }
    }
    val j3 = CoroutineScope(Dispatchers.IO).launch {
        for (i in 0 until 500) {
            incrementWithMutex()
        }
    }
    val j4 = CoroutineScope(Dispatchers.IO).launch {
        for (i in 0 until 500) {
            incrementWithMutex()
        }
    }

    fun run() {
        runBlocking {
            joinAll(j1,j2,j3,j4)
        }

        println("  No Mutex Tally: $counterNoMutex")
        println("With Mutex Tally: $counterWithMutex")
    }

}

fun main() {
    TestMutex().run()
}