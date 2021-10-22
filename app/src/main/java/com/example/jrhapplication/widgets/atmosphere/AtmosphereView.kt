package com.example.jrhapplication.widgets.atmosphere

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.os.Looper
import android.util.AttributeSet
import android.util.SparseArray
import android.view.View
import android.view.animation.*
import androidx.core.util.valueIterator
import com.example.jrhapplication.R
import kotlin.random.Random

/**
 * 氛围烘托
 */
class AtmosphereView : View {

    //    private var path = Path()
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
        setBackgroundColor(Color.parseColor("#FEFEFE"))
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

    private var mSrcRect = Rect(0, 0, 0, 0)
    private var mDesRect = Rect(0, 0, 0, 0)

    val atmosphereItems = SparseArray<AtmosphereItem>()


    private var mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.RED
    }

    private fun init(context: Context) {
        mContext = context
        prepareIcons()

    }

    private fun randomInterpolator(): TimeInterpolator {
        val random = Random.nextInt(8)
        println("AtmosphereView---random=$random")
        return when (random) {
//            0,4 ->AccelerateDecelerateInterpolator()
//            1,2 ->AccelerateInterpolator()
//            3 -> FastOutLinearInInterpolator()
//            5 -> FastOutSlowInInterpolator()
            else -> {
                LinearInterpolator()
            }
        }

    }

//    var evaluator: AtmosphereEvaluator? = null

    fun startAni(num: Int) {

        val evaluator = AtmosphereEvaluator(num % 2 == 0, mW / 3, mW, mH, getBitMap())

        val animator = ValueAnimator.ofObject(evaluator, evaluator.start(), evaluator.end())
//            path.moveTo(evaluator.startP.x, evaluator.startP.y)
        animator.duration = 5000
        animator.interpolator = randomInterpolator()
        val aniListener = ValueAnimator.AnimatorUpdateListener {
            val item = it.animatedValue as AtmosphereItem
            atmosphereItems.put(it.hashCode(), item)
//                path.cubicTo(
//                    evaluator.p1.x,
//                    evaluator.p1.y,
//                    evaluator.p2.x,
//                    evaluator.p2.y,
//                    item.pointF.x,
//                    item.pointF.y
//                )
            invalidateView()
        }
        animator.addUpdateListener(aniListener)
        animator.addListener(AtmosphereListener(aniListener.hashCode(), atmosphereItems))
        animator.start()

    }

    fun getBitMap() = defaultIcons[Random.nextInt(defaultIcons.size)]

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mW = measuredWidth
        mH = measuredHeight

        println("AtmosphereView---mw=$measuredWidth--mW=$measuredHeight")

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

//        evaluator?.let {
//        println("draw----------startP=${it.startP}---P1=${it.p1}--p2=${it.p2}--endp=${it.endP}")
//            mPaint.style = Paint.Style.FILL
//            mPaint.color = Color.parseColor("#983909")
//            canvas?.drawCircle(it.startP.x, it.startP.y, 20f, mPaint)
//            mPaint.color = Color.GREEN
//            canvas?.drawCircle(it.p1.x, it.p1.y, 20f, mPaint)
//            mPaint.color = Color.BLUE
//            canvas?.drawCircle(it.p2.x, it.p2.y, 20f, mPaint)
//            mPaint.color = Color.DKGRAY
//            canvas?.drawCircle(it.endP.x, it.endP.y, 20f, mPaint)
//            mPaint.color = Color.RED
//            mPaint.style = Paint.Style.STROKE
////            canvas?.drawPath(path, mPaint)
//        }


        atmosphereItems.valueIterator().forEach { item ->
            mSrcRect.run {
                left = 0
                top = 0
                right = item.getBitmapWith()
                bottom = item.getBitmapHeight()
            }

            mDesRect.run {
                left = item.pointF.x.toInt()
                top = item.pointF.y.toInt()
                right = (left + item.scale * item.getBitmapWith()).toInt()
                bottom = (top + item.scale * item.bitmap.height).toInt()
            }

            mPaint.alpha = item.alpha.toInt()

            println("AtmosphereView---onDraw--srcRect=$mSrcRect---desRect=$mDesRect")
            canvas?.drawBitmap(item.bitmap, mSrcRect, mDesRect, mPaint)
        }
    }

    private fun prepareIcons() {
        defaultIconIds.map {
            BitmapFactory.decodeResource(resources, it)
        }.also {
            defaultIcons.addAll(it)
        }
    }


    class AtmosphereListener(val key: Int, private val items: SparseArray<AtmosphereItem>) :
        AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
            super.onAnimationEnd(animation)
            items.remove(key)
        }

        override fun onAnimationCancel(animation: Animator?) {
            super.onAnimationCancel(animation)
            items.remove(key)
        }
    }

    /**
     * 刷新View
     */
    private fun invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            //  当前线程是主UI线程，直接刷新。
            invalidate()
        } else {
            //  当前线程是非UI线程，post刷新。
            postInvalidate()
        }
    }


}







