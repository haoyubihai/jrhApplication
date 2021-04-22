package com.live.operator

import java.util.concurrent.TimeUnit

abstract class Scheduler {

    abstract fun scheduleDirect(runnable: Runnable, delay: Long, timeUnit: TimeUnit)

    class RunnableTask(private val realRunnable: Runnable) : Runnable {

        override fun run() {
            realRunnable.run()
        }
    }

    companion object {
        val NEW_THREAD:Scheduler = NewThreadScheduler(RxThreadFactory())
        val MAIN_THREAD:Scheduler = MainThreadScheduler()
    }
}

