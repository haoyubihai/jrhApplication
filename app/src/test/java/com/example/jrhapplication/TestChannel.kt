package com.example.jrhapplication

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/9/24     2:17 PM
 * 用途:
 ***************************************
 */


fun main() {

    testChannelRendezvous()

}


fun testChannelRendezvous() = runBlocking {

    // 默认
    val rendezvousChannel = Channel<Int>(Channel.RENDEZVOUS)


    launch {
        repeat(10) {
            rendezvousChannel.send(it * it)
        }
    }

    repeat(10) {
        println("-------Receive------${rendezvousChannel.receive()}")
    }

}



