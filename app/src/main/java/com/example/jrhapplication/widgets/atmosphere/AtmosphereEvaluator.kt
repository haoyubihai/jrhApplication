package com.example.jrhapplication.widgets.atmosphere

import android.animation.TypeEvaluator
import android.graphics.Bitmap
import android.graphics.PointF
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt


/**
 *@property lrcViewWidth 歌词占据屏幕的宽 （调节氛围营造的显示区域）
 * @property sw Int 控件宽
 * @property sh Int 控件高
 * @property bitmap Bitmap 绘制的bitmap
 * @constructor
 */
class AtmosphereEvaluator(
    val isLeft: Boolean,
    val lrcViewWidth: Int,
    val sw: Int,
    val sh: Int,
    val bitmap: Bitmap
) :
    TypeEvaluator<AtmosphereItem> {


    var p1: PointF = PointF(0f, 0f)
    var p2: PointF = PointF(0f, 0f)
    var startP: PointF = PointF(0f, 0f)
    var endP: PointF = PointF(0f, 0f)


    init {
        resetP()

    }

    private fun resetP() {
        start()
        end()
        if (isLeft) {

            p1.x = startP.x * (1 - 1.42f)
            p1.y = startP.y * (1 - 0.03f)


            p2.x = endP.x * 0.91f
            p2.y = endP.y * (1 - 0.69f)

        } else {
            p1.x = startP.x * (1 + 1.22f)
            p1.y = startP.y * (1 - 0.03f)

            p2.x = endP.x * (0.91f)
            p2.y = endP.y * (1 - 0.09f)

        }


    }

    /**
     * 调节终点的坐标
     */
    private val adjustHeight = bitmap.height * 2
    private fun realH(): Float = (sh - bitmap.height).toFloat()
    private fun realW(): Float = (sw - lrcViewWidth).toFloat()

    private fun bottomMidPointF(): PointF {
        return PointF(sw / 2f, realH())
    }

    private fun createBezierPointF(min: Int, max: Int): PointF {
        return PointF(
            (randomRange(min, max) / 100f) * (realW() / 2f),
            (randomRange(min, max) / 100f) * realH()
        )
    }

    private fun randomRange(min: Int, max: Int): Int {
        return Random.nextInt(max) % (max - min + 1) + min
    }


    /**
     * 左侧顶部
     * @return PointF
     */
    private fun leftTopMidPointF(): PointF {
        val y = (bitmap.height * 3 - Random.nextInt(bitmap.height * 2, bitmap.height * 5)).toFloat()
        val adjust = if (y > 0) y * 0.8f else 0f
        var x = realW() / 4 - nextInt(0, realW().toInt())
        if (x > 0 && y > 0) {
            x -= adjust
        }
        return PointF(x, y)
    }

    /**
     * 右侧顶部
     * @return PointF
     */
    private fun rightTopMidPointF(): PointF {
        val y = (bitmap.height * 3 - Random.nextInt(bitmap.height * 2, bitmap.height * 5)).toFloat()
        val adjust = if (y > 0) y * 0.8f else 0f
        var x = sw / 2 + lrcViewWidth / 2f + nextInt(0, realW().toInt())
        if (x > (sw / 2 + lrcViewWidth / 2f) && y > 0) {
            x += adjust
        }
        return PointF(x, y)
    }

    val alphaAdjust = Random.nextInt(20) / 100f
    override fun evaluate(
        t: Float,
        startValue: AtmosphereItem?,
        endValue: AtmosphereItem?
    ): AtmosphereItem {
        var fraction = 1 - t
        val resultAlpha = if (fraction < alphaAdjust) 0f else fraction
        var alpha = 255f * resultAlpha
        var scale = 0.8f
        val pointF = PointF(0f, 0f)

        if (startValue != null && endValue != null) {
            val x =
                startValue.pointF.x * fraction * fraction * fraction + (3 * p1.x * t * fraction * fraction) + (3 * p2.x * t * t * fraction) + t * t * t * endValue.pointF.x
            pointF.x = x

            val y =
                startValue.pointF.y * fraction * fraction * fraction + (3 * p1.y * t * fraction * fraction) + (3 * p2.y * t * t * fraction) + t * t * t * endValue.pointF.y

            pointF.y = y

        }

        return AtmosphereItem(bitmap, alpha, scale, pointF)
    }

    fun start(): AtmosphereItem {

        if (startP.x == 0f) {
            val mid = bottomMidPointF()
            startP.x = mid.x - bitmap.width / 2
            startP.y = mid.y + bitmap.height
        }

        return AtmosphereItem(bitmap, alpha = 255f, scale = 1f, startP)
    }


    fun end(): AtmosphereItem {
        if (endP.x == 0f) {
            endP = if (isLeft) leftTopMidPointF() else rightTopMidPointF()
        }
        return AtmosphereItem(bitmap, alpha = 255f, scale = 1f, endP)
    }

}