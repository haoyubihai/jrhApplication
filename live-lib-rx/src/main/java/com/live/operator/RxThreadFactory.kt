package com.live.operator

import java.util.concurrent.ThreadFactory

class RxThreadFactory: ThreadFactory {
    override fun newThread(runnable: Runnable): Thread {
        return RxThread(runnable,"RX")
    }

    class RxThread(private val runnable: Runnable, private val threadName:String) :Thread(runnable, threadName)
}