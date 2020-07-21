package com.example.jrhapplication.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * 存在问题 计时不准确
 * for (i in 0..totalTime) {
 * emit(i)
 * delay(1)
 * timeIndex++
 * }
 * 这里耗费的时间有点多 设计不合理
 *
 * 建议:
 * 1：倒计时以 s 为单位，放弃 ms
 * 2：可以只保留倒计时时功能，去掉间隔多少时间回调
 *
 */
class TimerViewModelImpl : TimerViewModel() {

    private val timerMap = mutableMapOf<String, Job>()


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
     * @param overAction  totalTime正常倒计时结束 或者中途取消timer 返回
     */
    override fun startTimer(
        key: String,
        totalTime: Long,
        completeAction: (Key: String) -> Unit,
        initDelayTime: Long?,
        intervalTime: Long?,
        intervalAction: ((Key: String, time: Long) -> Unit)?
    ) {
        checkAndStopTimer(key)
        val job = viewModelScope.launch {
            initDelayTime?.let { delay ->
                if (delay > 0) {
                    delay(delay)
                }
            }
            var timeIndex = -1L
            flow {
                for (i in 0..totalTime) {
                    emit(i)
                    delay(1)
                    timeIndex++
                }
            }.collect { time ->
                intervalTime?.let {
                    if (timeIndex == it) {
                        intervalAction?.invoke(key, time)
                        timeIndex = -1L
                    }
                }
                if (time == totalTime) {
                    completeAction(key)
                }
            }
        }
        timerMap[key] = job
    }

    override fun stopTimer(key: String) {
        timerMap[key]?.run {
            if (isActive) {
                cancel()
            }
        }
    }

    override fun stopAllTimer() {
        timerMap.keys.asIterable().asSequence().forEach { key -> stopTimer(key) }
        timerMap.clear()
    }
}