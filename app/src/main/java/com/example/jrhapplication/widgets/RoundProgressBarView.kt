package com.example.jrhapplication.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/6/16     7:02 PM
 * 用途:
 ***************************************
 */
class RoundProgressBarView(context: Context,val colorPaint:Int, val progress:Int) : View(context) {

    val paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = colorPaint
        }
    }

    var roundRect: RectF?=null

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val per = progress/100f
        roundRect = RectF(0f,0f,measuredWidth.toFloat()*per,measuredHeight.toFloat())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.run {
            roundRect?.let {
                drawRoundRect(it,measuredWidth/2f,measuredWidth/2f,paint)
            }
        }
    }

}