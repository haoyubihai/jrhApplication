package com.example.jrhapplication.widgets.atmosphere

import android.animation.*
import android.content.Context
import android.graphics.*
import android.os.Looper
import android.util.AttributeSet
import android.util.Property
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.animation.addListener
import androidx.core.util.valueIterator
import androidx.core.view.updateLayoutParams
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import com.example.jrhapplication.R
import com.example.jrhapplication.utils.DpUtils
import kotlin.random.Random

/**
 * 氛围烘托
 */
class AtmosphereViewLayout : RelativeLayout {

    private var mContext: Context? = null


    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int = 0) : super(
        context,
        attrs,
        defStyle
    ) {
        init(context)

    }

    private val defaultIconIds = arrayListOf(
        R.drawable.ks_bdbc_pic_effects1,
        R.drawable.ks_bdbc_pic_effects2,
        R.drawable.ks_bdbc_pic_effects3,
        R.drawable.ks_bdbc_pic_effects4,
        R.drawable.ks_bdbc_pic_effects5,
        R.drawable.ks_bdbc_pic_effects6,
    )

    private val defaultIcons = mutableListOf<Bitmap>()


    private var mW: Int = 0
    private var mH: Int = 0


    val atmosphereItems = SparseArray<AtmosphereItem>()


    fun startAni(num: Int) {
        val random = Random.nextInt(defaultIcons.size)

        for (i in 0 until num) {
            val index = (random + i) % defaultIcons.size
            val bitmap = defaultIcons[index]
            val imageView = ImageView(context).apply {
                setImageBitmap(bitmap)
                layoutParams = LayoutParams(DpUtils.dp2px(48f), DpUtils.dp2px(48f))
                toStart()
            }

            addView(imageView, createLayoutParam())

            postDelayed({startViewAni(imageView, i % 2 == 0)},200L*i)

        }
    }

    private fun startViewAni(child: View, left: Boolean) {


        resetEnd(left)

        startViewAniFrame(child,left)
//        var transXAni: ObjectAnimator? = null
//        if (left) {
//            transXAni = ObjectAnimator.ofFloat(
//                child,
//                "translationX",
//                startValue(),
//                endPointF.x
//            ).apply {
//                interpolator = randomInterpolator()
//            }
//        } else {
//            transXAni = ObjectAnimator.ofFloat(
//                child,
//                "translationX",
//                startValue(),
//                endPointF.x
//            ).apply {
//                interpolator = LinearOutSlowInInterpolator()
//            }
//        }
//        val tranYAni =
//            ObjectAnimator.ofFloat(child, "translationY", startPointF.y + bitMapH(), endPointF.y)
//                .apply {
//                    interpolator = LinearOutSlowInInterpolator()
//                }
//
//        transXAni?.let { tranx ->
//
//            AnimatorSet().apply {
//                play(tranx).with(tranYAni)
//                duration = 10000
//                addListener(
//                    onEnd = {
//                        removeView(child)
//                    },
//                    onStart = {}
//                )
////                start()
//            }

//        }
    }



    private fun startViewAniFrame(child: View, left: Boolean) {
        val startX = Keyframe.ofFloat(0f, startPointF.x - bitMapW()/2)
        val random = Random.nextInt(10,40)/100f
        val lMid = Keyframe.ofFloat(random, endPointF.x - bitMapW()/2)
        val rMid = Keyframe.ofFloat(random, mW-bitMapW().toFloat())
        val mid = if (left) lMid else rMid
        val endX = Keyframe.ofFloat(1f, endPointF.x)

        val startY = Keyframe.ofFloat(0f, startPointF.y)
        val midY = Keyframe.ofFloat(random, mH*5/6f)
        val endY = Keyframe.ofFloat(1f, endPointF.y)

        val alphaEndRandom = Random.nextInt(60,80)/100f
        val startAlpha = Keyframe.ofFloat(0f,0f)
        val mid1alpha = Keyframe.ofFloat(0.1f,0f)
        val midAlpha = Keyframe.ofFloat(Random.nextInt(30,50)/100f,1f)
        val endValue = Keyframe.ofFloat(alphaEndRandom,0f)

        val scaleEndRandom = Random.nextInt(50,80)/100f
        val scaleStartRandom = Random.nextInt(70,85)/100f
        val startScale = Keyframe.ofFloat(0f,scaleStartRandom)
        val endScale = Keyframe.ofFloat(scaleEndRandom,1f)


        val holderX = PropertyValuesHolder.ofKeyframe("translationX", startX,mid, endX)
        val holderY = PropertyValuesHolder.ofKeyframe("translationY", startY,midY, endY)
        val alpha = PropertyValuesHolder.ofKeyframe("alpha", startAlpha,mid1alpha,midAlpha,endValue)
        val scaleX = PropertyValuesHolder.ofKeyframe("scaleX", startScale,endScale)
        val scaleY = PropertyValuesHolder.ofKeyframe("scaleY", startScale,endScale)

        val randomTime = Random.nextInt(3,5)
        ObjectAnimator.ofPropertyValuesHolder(child, holderX,holderY,alpha).apply {
            duration = randomTime*1000L
            interpolator = LinearInterpolator()
            addListener(onEnd = {
                removeView(child)
            })
            addUpdateListener {
                println("vvvvvvvvvvv=${it.values}")
            }

        }. start()
    }

    private fun startValue() = startPointF.x - bitMapW() / 2

    private fun realW() = mW - lrcViewWidth()


    fun createLayoutParam() = LayoutParams(DpUtils.dp2px(86f), DpUtils.dp2px(86f))



    private var startPointF = PointF(0f, 0f)
    private var endPointF = PointF(0f, 0f)

    private fun bitMapW() = DpUtils.dp2px(86f)
    private fun bitMapH() = DpUtils.dp2px(86f)
    private fun lrcViewWidth() = mW / 3
    private fun start(): PointF {
        startPointF = PointF(mW / 2f, mH.toFloat())
        return startPointF
    }

    private fun resetEnd(left: Boolean) {
        if (left) {
            leftEnd()
        } else {
            rightEnd()
        }
    }

    private fun leftEnd(): PointF {
        endPointF = PointF(mW / 4f, 0f - bitMapH()).apply {
            var v = x - (1 - Random.nextFloat()) * mW
            if (v > realW() / 8) {
                v = realW() / 8f-bitMapW()
            }
            x = 0f
        }

        return endPointF
    }

    private fun rightEnd(): PointF {
        endPointF = PointF(3 * mW / 4f, 0f - bitMapH()).apply {
            x += (1 - Random.nextFloat()) * (mW / 4f)
        }
        return endPointF
    }


    fun ImageView.toStart() {
        translationX = start().x - bitMapW() / 2
        translationY = start().y-bitMapH()
    }

    private fun init(context: Context) {
        mContext = context
        prepareIcons()

    }

    private fun randomInterpolator(): TimeInterpolator {
        val random = Random.nextInt(8)
        println("AtmosphereView---random=$random")
        return when (random) {
            0, 4 -> AccelerateDecelerateInterpolator()
            1, 2 -> AccelerateInterpolator()
            3 -> FastOutLinearInInterpolator()
            5 -> FastOutSlowInInterpolator()
            else -> {
                FastOutSlowInInterpolator()
            }
        }

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mW = measuredWidth
        mH = measuredHeight

        println("AtmosphereView---mw=$measuredWidth--mW=$measuredHeight")

    }


    private fun prepareIcons() {


        defaultIconIds.map {
            BitmapFactory.decodeResource(resources, it)
        }.also {
            defaultIcons.addAll(it)
        }
    }


}







