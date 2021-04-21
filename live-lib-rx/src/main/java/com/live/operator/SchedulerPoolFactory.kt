package com.live.operator

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ThreadFactory
import java.util.concurrent.atomic.AtomicReference

object SchedulerPoolFactory {
    val PURGE_THREAD = AtomicReference<ScheduledExecutorService>()

    fun create(factory: ThreadFactory):ScheduledExecutorService{
        return Executors.newScheduledThreadPool(1,factory)
    }
}