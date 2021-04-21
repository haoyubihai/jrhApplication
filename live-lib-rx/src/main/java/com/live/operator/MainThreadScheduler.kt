package com.live.operator

import android.os.Handler
import android.os.Looper
import java.util.concurrent.TimeUnit

class MainThreadScheduler:Scheduler() {
    private val handler = Handler(Looper.getMainLooper())
    override fun scheduleDirect(runnable: Runnable, delay: Long, timeUnit: TimeUnit) {
       handler.post(runnable)
    }
}