package com.example.jrhapplication

import kotlinx.coroutines.*
import java.lang.Thread.sleep
import kotlin.coroutines.resumeWithException
import kotlin.random.Random


fun main() {
    run()
}

suspend fun task() = suspendCancellableCoroutine<Boolean> {
//    sleep(Random.nextLong(3000))

    try {
        if (Random.nextInt() % 2 == 0) {
            it.resumeWith(Result.success(true))
        } else {
            it.resumeWithException(Throwable("error"))
//            it.resumeWith(Result.success(false))
            it.resume(false){
                println("else-----$it")
            }

        }
    } catch (e: Exception) {
        println("exception---0000=${e.message}")
    }

}


fun run() = runBlocking {

    try {
        val tasks = mutableListOf<Deferred<Boolean>>()
        for (i in 0 until 5) {
            async {
                task()
            }.let {
                tasks.add(it)
            }
        }

        var success = true

        try {
            tasks.forEach {
                success = success && it.await()
                println("for------success=$success")
            }
        } catch (e: Exception) {
            success = false
            println("exception---11111=${e.message}")
        }


        if (success) {
            println("success")
        } else {
            println("failed")
        }
    } catch (e: Exception) {
        println("exception=${e.message}")
    }
}