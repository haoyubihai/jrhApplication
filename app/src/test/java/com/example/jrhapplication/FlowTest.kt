package com.example.jrhapplication

import android.support.v4.app.INotificationSideChannel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/9/9     3:17 PM
 * 用途:
 ***************************************
 */


fun main() {
  testSingle()
    toList()
}

val dataList = 0..10

val singleData = 1

fun testTryCatchFlow() = runBlocking {
    flow {
            emit(1/0)




    }.catch {
        println("error")
    }.collectLatest {
        println("collect--$it")
    }

}


fun testCallBackFlow() = runBlocking {
    callbackFlow {

        sendBlocking(1)

        awaitClose {

        }
    }
}

fun testChannelFlow() = runBlocking {
    val channelFlow = channelFlow<Int> {send(1)  }

    channelFlow.collectLatest {
    }

}


/**
 * 只能发送 接受 1个元素
 */
fun testSingle() = runBlocking {
    println(flow { emit(0) }.single())
}

fun toList() = runBlocking {
    val listMap = dataList.asFlow().map {
        it*it
    }.toList()

    println(listMap)

    val listOnEach = dataList.asFlow().onEach {
        it*it
    }.toList()

    println(listOnEach)
}