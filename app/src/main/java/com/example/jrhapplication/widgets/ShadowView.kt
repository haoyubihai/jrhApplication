package com.example.jrhapplication.widgets

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import com.jrhlive.library.dp2px

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/3/23     11:21 AM
 * 用途:
 ***************************************
 */

class ShadowView (context: Context, attrs: AttributeSet):FrameLayout(context, attrs){

    var  mPaint :Paint
    lateinit var rectF:RectF
    val radius = dp2px(10).toFloat()
    val ext = dp2px(2).toFloat()
    init {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {

            strokeWidth = dp2px(2).toFloat()
            color = Color.RED
            setShadowLayer(dp2px(10).toFloat(),dp2px(10).toFloat(),dp2px(10).toFloat(),Color.RED)
        }


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        rectF = RectF(0f,0f,width.toFloat()+ext,height.toFloat()+ext)
    }

    override fun dispatchDraw(canvas: Canvas?) {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        canvas?.drawRoundRect(rectF,radius,radius,mPaint)
        super.dispatchDraw(canvas)
    }


}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class ViewShadowProvider : ViewOutlineProvider(){
    override fun getOutline(view: View?, outline: Outline?) {


    }

}

