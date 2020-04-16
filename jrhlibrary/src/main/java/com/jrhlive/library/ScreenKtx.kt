package com.jrhlive.library

import android.content.Context
import android.view.View

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/3/23     11:27 AM
 * 用途:
 ***************************************
 */

val Context.screenWidth
    get() = resources.displayMetrics.widthPixels

/**
 * The absolute height of the available display size in pixels
 */
val Context.screenHeight
    get() = resources.displayMetrics.heightPixels


fun Context.dp2px(dp: Int): Int = dp2px(dp.toFloat())

fun Context.dp2px(dp: Float): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

fun Context.px2dp(px: Int): Int = px2dp(px.toFloat()).toInt()

fun Context.px2dp(px: Float): Float {
    val scale = resources.displayMetrics.density
    return (px / scale + 0.5f)
}


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