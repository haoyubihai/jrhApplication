package com.example.jrhapplication.widgets

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.jrhlive.library.dp2px

/**
 ***************************************
 * 项目名称:呼吸动画 圆形
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/3/31     4:38 PM
 * 用途:
 ***************************************
 */

class BreathView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    private var mRadius = dp2px(20).toFloat()

    private var mDefaultRadius = mRadius

    private val startColor = Color.parseColor("#ffe600")
    private val endColor = Color.parseColor("#66ffee00")


    //扩散半径
    private val mDiffWidth = dp2px(10).toFloat()
    private val mDuration = 933L

    private var outAni: Animator? = null
    private var inAni: Animator? = null
    private var colorOutAni: Animator? = null
    private var colorInAni: Animator? = null

    private var paintColor = startColor
    private var isPlaying = false
    private val mPaint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            color = Color.parseColor("#ffe600")
            setGradientShader()
        }
    }

    private fun Paint.setGradientShader(startSetColor: Int? = null, endSetColor: Int? = null) {
        shader = RadialGradient(
            measuredWidth / 2.0f, measuredHeight / 2.0f,
            mRadius,
            startColor,
            paintColor,
            Shader.TileMode.CLAMP

        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        reset()
    }

    private fun reset() {
        mRadius = measuredWidth.coerceAtMost(measuredHeight).toFloat() / 2.0f - mDiffWidth
        mDefaultRadius = mRadius
        mPaint.setGradientShader(startColor, endColor)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            canvas.drawCircle(measuredWidth / 2.0f, measuredHeight / 2.0f, mRadius, mPaint)
        }
    }

    fun startAni() {
        if (isPlaying) return
        //扩散动画
        outAni = ValueAnimator.ofFloat(0f, mDiffWidth)
            .setDuration(mDuration)
            .apply {
                addUpdateListener {
                    mRadius = mDefaultRadius + (it.animatedValue as Float)
                    mPaint.setGradientShader()
                    invalidate()
                }
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        if (!isPlaying) return
                        //重复播放
                        startDelay = mDuration
                        start()

                    }
                })
            }

        //收回
        inAni = ValueAnimator.ofFloat(mDiffWidth, 0f)
            .setDuration(mDuration)
            .apply {
                startDelay = mDuration
                addUpdateListener {
                    mRadius = mDefaultRadius + (it.animatedValue as Float)
                    mPaint.setGradientShader()
                    invalidate()
                }
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        if (!isPlaying) return
                        //重复播放
                        startDelay = mDuration
                        start()

                    }
                })
            }

        //颜色扩散
        colorOutAni = ValueAnimator.ofObject(ArgbEvaluator(), startColor, endColor)
            .setDuration(mDuration)
            .apply {
                addUpdateListener {
                    paintColor = it.animatedValue as Int
                    mPaint.color = paintColor
                    invalidate()
                }
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        if (!isPlaying) return
                        startDelay = mDuration
                        start()
                    }

                })
            }
        //颜色收回
        colorInAni = ValueAnimator.ofObject(ArgbEvaluator(), endColor, startColor)
            .setDuration(mDuration)
            .apply {
                startDelay = mDuration
                addUpdateListener {
                    paintColor = it.animatedValue as Int
                    mPaint.color = paintColor
                    invalidate()
                }
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        if (!isPlaying) return
                        startDelay = mDuration
                        start()
                    }

                })
            }


        outAni?.start()
        inAni?.start()
        colorOutAni?.start()
        colorInAni?.start()
        isPlaying = true

    }

    fun stopAni() {
        isPlaying = false
        outAni?.let {
            it.cancel()
        }
        inAni?.let {
            it.cancel()
        }
        colorOutAni?.let {
            it.cancel()
        }
        colorInAni?.let {
            it.cancel()
        }
        reset()
        invalidate()
    }


}