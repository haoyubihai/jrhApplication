package com.example.jrhapplication.ktx

import android.view.View

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/8/20     3:18 PM
 * 用途:
 ***************************************
 */


fun View.dp2px(dp: Int): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

fun View.dp2px(dp: Float): Float {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f)
}

fun View.px2dp(px: Int): Int {
    return px2dp(px.toFloat()).toInt()
}

fun View.px2dp(px: Float): Float {
    val scale = resources.displayMetrics.density
    return (px / scale + 0.5f)
}
