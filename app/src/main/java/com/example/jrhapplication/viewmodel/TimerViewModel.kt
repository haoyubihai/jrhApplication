package com.example.jrhapplication.viewmodel

import androidx.lifecycle.ViewModel
import java.security.Key

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/7/16     10:03 AM
 * 用途:
 ***************************************
 */
abstract class TimerViewModel : ViewModel() {

    /**
     * @param key  timer key
     * @param initDelayTime 延迟 initDelayTime ms 启动
     * @param intervalTime  间隔 intervalTime  ms 回调一次当前时间
     * @param totalTime     总共倒计时 totalTime ms
     * @param completeAction  totalTime正常倒计时结束返回
     * @param intervalAction 每隔 intervalTime ms 回调一次当前时间
     */
    abstract fun startTimer(
        key: String,
        totalTime: Long,
        completeAction: (Key: String) -> Unit,
        initDelayTime: Long?=0L,
        intervalTime: Long?=null,
        intervalAction: ((Key: String, time: Long) -> Unit)? = null
    )

    abstract fun stopTimer(key: String)
    abstract fun stopAllTimer()
}