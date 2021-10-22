package com.example.jrhapplication

import kotlinx.coroutines.internal.synchronized

class TestSynchronized {

    val a = "A"
    val b = "B"


    fun printResult() {
        println("time-${System.currentTimeMillis()}--name=${Thread.currentThread().name}")
        Thread.sleep(5000)
    }

    @Synchronized
    fun aa() {
    }

    fun ss1() {
        Thread {
        //锁对象
        kotlin.synchronized(a) {
            printResult()
        }
        }.start()

    }

    fun ss2() {
        Thread {
            //锁对象
            kotlin.synchronized(a) {
                printResult()
            }
        }.start()
    }

    fun ss3() {
        Thread {
            //锁对象
            kotlin.synchronized(b) {
                printResult()
            }
        }.start()
    }


    fun bb() {
        kotlin.synchronized(TestSynchronized::class.java) {

        }
    }

    companion object {

    }


}

fun main() {
    val t = TestSynchronized()
    t.ss1()
    t.ss2()
    t.ss3()

}