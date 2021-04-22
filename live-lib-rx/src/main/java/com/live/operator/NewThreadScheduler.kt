package com.live.operator

import java.util.concurrent.ThreadFactory
import java.util.concurrent.TimeUnit

class NewThreadScheduler(private val threadFactory: ThreadFactory):Scheduler() {
    override fun scheduleDirect(runnable: Runnable, delay: Long, timeUnit: TimeUnit) {
        SchedulerPoolFactory.create(threadFactory).schedule(runnable, delay, timeUnit)
    }

}