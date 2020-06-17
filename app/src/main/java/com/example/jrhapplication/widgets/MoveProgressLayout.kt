package com.example.jrhapplication.widgets

import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.LinearLayout

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/6/17     10:52 AM
 * 用途:
 ***************************************
 */
class MoveProgressLayout(context: Context, attrs: AttributeSet?) : RoundLinearLayout(context,attrs) {


    private var color1 = Color.parseColor("#ff0000")
    private var color2 = Color.parseColor("#00ff00")

    private val paint1 by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }
    private val paint2 by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }

    fun initViews(  radius:Float, color1:Int, color2:Int){
        this.color1 = color1
        this.color2 = color2
        setRadius(radius)
        addColorViews()
    }

    private fun createMoveViewLay() = LinearLayout(context)

    private fun createColorfulView() = ColorfulView(context,measuredWidth,paint1.apply { color=color1 },paint2.apply { color=color2 })


    private fun addColorViews()=addView(createMoveViewLay().apply {
            addView(createColorfulView())
            addView(createColorfulView())
        },ViewGroup.LayoutParams(measuredWidth*2, ViewGroup.LayoutParams.MATCH_PARENT))

    fun startAni(durationTime:Long=5000) {
        val moveLay = getChildAt(0) as LinearLayout
        val ani = ObjectAnimator.ofFloat(moveLay, "translationX", 0f, measuredWidth.toFloat())
        ani.apply {
            duration = durationTime
            repeatMode = ValueAnimator.RESTART
            repeatCount= ValueAnimator.INFINITE
            interpolator = LinearInterpolator()
        }
        ani.start()
    }

}