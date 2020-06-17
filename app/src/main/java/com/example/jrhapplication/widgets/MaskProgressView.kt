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
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.jrhlive.library.dp2px

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/6/16     6:39 PM
 * 用途:
 ***************************************
 */
class MaskProgressView : RelativeLayout {

    constructor(context: Context?, attrs: AttributeSet?):super(context, attrs){

    }

    fun initViews() {
        drawBg()
        drawSecondProgress()
        drawProgress(60)
    }


    private fun drawProgress(progress: Int) {
        addView(RoundProgressBarView(context, Color.parseColor("#ffff0000"), 60))
    }

    private fun drawSecondProgress() {
        val roundView = MoveProgressLayout(context,null)
        val params = ViewGroup.LayoutParams((measuredWidth * 0.8f).toInt(),ViewGroup.LayoutParams.MATCH_PARENT)
        addView(roundView, params)
    }



    private fun drawBg() {
        addView(RoundProgressBarView(context, Color.parseColor("#ff00ff00"), 100))
    }

    fun startAni() {
        val maskView = getChildAt(1) as MoveProgressLayout
        maskView.post {
            maskView.initViews(dp2px(15).toFloat(),Color.parseColor("#889096"),Color.parseColor("#763564"))
            maskView.startAni(1000*10)
        }

    }
}