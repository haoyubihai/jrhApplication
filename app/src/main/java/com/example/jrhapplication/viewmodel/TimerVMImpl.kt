package com.example.jrhapplication.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.receiveOrNull
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlin.math.roundToInt

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/7/20     5:54 PM
 * 用途:
 * 1:多个计时器处理
 * 2:
 * 风险:现在使用的api 被标记为    @ExperimentalCoroutinesApi
 ***************************************
 */
class TimerVMImpl : TimerViewModel() {

    private val timerMap = mutableMapOf<String, ReceiveChannel<Unit>>()


    @ExperimentalCoroutinesApi
    private fun checkAndStopTimer(key: String) {
        stopTimer(key)
        timerMap.remove(key)
    }

    /**
     * @param key  timer key
     * @param initDelayTime 延迟 initDelayTime ms 启动
     * @param intervalTime  间隔 intervalTime  ms 回调一次当前时间
     * @param totalTime     总共倒计时 totalTime ms
     * @param completeAction  totalTime正常倒计时结束返回
     * @param intervalAction 每隔 intervalTime ms 回调一次当前时间
     */
    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    override fun startTimer(
        key: String,
        totalTime: Long,
        completeAction: (Key: String) -> Unit,
        initDelayTime: Long?,
        intervalTime: Long?,
        intervalAction: ((Key: String, time: Long) -> Unit)?

    ) {
        checkAndStopTimer(key)
        val channel = ticker(intervalTime ?: 1000, initDelayTime ?: 0L)
        val times = (totalTime.toFloat() / (intervalTime ?: 1000)).roundToInt() + 1
        repeat(times) { timeIndex ->
            val job = viewModelScope.launch {
                val result = channel.receiveOrNull()

                if (result != null) {
                    intervalAction?.invoke(key, timeIndex * (intervalTime ?: 1000))
                }
                if (result != null && timeIndex == times - 1) {
                    completeAction.invoke(key)
                }
            }
            timerMap[key] = channel
        }
    }

    @ExperimentalCoroutinesApi
    override fun stopTimer(key: String) {
        timerMap[key]?.run {
            if (!isClosedForReceive) {
                cancel()
            }
        }
    }

    override fun stopAllTimer() {
        timerMap.keys.asIterable().asSequence().forEach { key -> stopTimer(key) }
        timerMap.clear()
    }

    private var timeJob: Job?=null

    /** 倒计时*/
    fun timeDown(seconds: Int, action: () -> Unit) {
        timeJob?.cancel()
            timeJob = viewModelScope.launch {
                (1..seconds).asFlow().onEach { delay(1000) }.collect { time ->
                    if (time == seconds) {
                        action()
                    }
                }
            }
        }

    /** 取消倒计时*/
    fun cancelTimer() = timeJob?.cancel()
}

