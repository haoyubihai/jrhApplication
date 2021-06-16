package com.example.jrhapplication.ui

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ExpandableListView
import android.widget.TextView
import androidx.appcompat.widget.ViewUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.jrhapplication.APP
import com.example.jrhapplication.R
import com.example.jrhapplication.ui.fragment.vpFragment
import kotlinx.android.synthetic.main.activity_recyclerview_vp.*
import java.util.*

/**
 * 用recyclerView实现ViewPager效果
 */
class RecyclerviewVpActivity : BaseActivity() {

//
//    var isScrollAble = true
//    var canScrollRight = false
//    var canScrollLeft = false
//    var mAdapter: ItemAdapter? = null
//    private var snapHelper: MyPagerSnapHelper? = null
//    private var mLayoutManager: MyLinearLayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_vp)

//        val fragments = mutableListOf<Fragment>()
//        repeat(5){
//            fragments.add(vpFragment.newInstance("$it","$it"))
//        }
//
//        val adapter = PageAdapter(this,fragments)
//        vp.adapter = adapter
//

//        btnNoScroll.setOnClickListener {
//            isScrollAble = !isScrollAble
////            snapHelper?.canScrollable = isScrollAble
////            mLayoutManager?.canScrollable = isScrollAble
//            if (!isScrollAble) {
//
//                canScrollRight = false
//                canScrollLeft = false
//            }
//
//        }
//        btnNext.setOnClickListener {
//            canScrollRight = true
//            canScrollLeft = false
//
//        }
//        btnPre.setOnClickListener {
//            canScrollLeft = true
//            canScrollRight = false
//        }
//
//        initRc()

    }

//
//    val datas = mutableListOf("android", "java", "ios", "kotlin", "python")
//    private fun initRc() {
//
//
//        repeat(10){
//            datas.add("item$it")
//        }
//        mAdapter = ItemAdapter(datas)
//        mLayoutManager =
//            MyLinearLayoutManager(this@RecyclerviewVpActivity, RecyclerView.HORIZONTAL, false)
//        rcList.apply {
//            layoutManager = mLayoutManager
//            adapter = mAdapter
//        }
//
//        snapHelper = MyPagerSnapHelper().apply {
//            attachToRecyclerView(rcList)
//        }
//
//        rcList.addOnItemTouchListener(onItemTouchListener)
//
//        rcList.addOnScrollListener(object :RecyclerView.OnScrollListener(){
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//
//                Log.e("rcList","$newState=====onScrollStateChanged")
//                state = newState
//            }
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//                Log.e("rcList","$dx=====onScrolled")
//
////                isScrollLeft = dx>=0
////                isScrollRight = dx<=0
//
//
//            }
//        })
//
//    }
//
//    var isScrollRight = false
//    var isScrollLeft = false
//    var lastUpTime = 0L
//    var lastDownTime = 0L
//    var state = 0
//    var currentPositon = 0
//
//    val onItemTouchListener = object : RecyclerView.OnItemTouchListener {
//        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
//        }
//
//        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
//            checkDown(e)
//            when (e.action) {
//                MotionEvent.ACTION_MOVE -> {
//                    updateX(e.x)
//                }
//                MotionEvent.ACTION_UP->{
//                    lastX1 = 0f
//                    lastX2 = 0f
//                    lastUpTime = System.currentTimeMillis()
//                }
//            }
//
////            if (state==RecyclerView.SCROLL_STATE_DRAGGING||state==RecyclerView.SCROLL_STATE_SETTLING){
////                return false
////            }
////
////            if (isScrollRight && canScrollLeft){
////                currentPositon--
////                if (currentPositon<=0){
////                    currentPositon==0
////                }
////            }
////            if (isScrollLeft && canScrollRight){
////                currentPositon++
////                if (currentPositon>=datas.size-1){
////                    currentPositon=datas.size-1
////                }
////            }
////
////            if (isScrollRight && canScrollLeft){
////                rcList.smoothScrollToPosition(currentPositon)
////            }
////            if (isScrollLeft && canScrollRight){
////                rcList.smoothScrollToPosition(currentPositon)
////            }
//
//            mLayoutManager?.canScrollable = isScrollRight && canScrollLeft || isScrollLeft && canScrollRight
//
//            return true
//        }
//
//        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
//        }
//
//    }
//
//    private fun checkDown(event: MotionEvent) {
//        GestureDetector(this@RecyclerviewVpActivity, object : GestureDetector.OnGestureListener {
//            override fun onShowPress(e: MotionEvent?) {
//            }
//
//            override fun onSingleTapUp(e: MotionEvent?): Boolean {
//                return false
//            }
//
//            override fun onDown(e: MotionEvent?): Boolean {
//                Log.e("onTouchEvent","onDown")
//                lastX1 = e?.x?:0f
//                lastDownTime = System.currentTimeMillis()
//                return false
//            }
//
//            override fun onFling(
//                e1: MotionEvent?,
//                e2: MotionEvent?,
//                velocityX: Float,
//                velocityY: Float
//            ): Boolean {
//                return false
//            }
//
//            override fun onScroll(
//                e1: MotionEvent?,
//                e2: MotionEvent?,
//                distanceX: Float,
//                distanceY: Float
//            ): Boolean {
//                return false
//            }
//
//            override fun onLongPress(e: MotionEvent?) {
//
//            }
//
//        }).onTouchEvent(event)
//    }
//
//    var lastX1 = 0f
//    var lastX2 = 0f
//    private fun updateX(x: Float) {
//
//        if (lastX1 == 0f) {
//            lastX1 = x
//            return
//        }
//
//        lastX2 = x
//
//        if (lastX1 > 0 && lastX2 > 0) {
//            isScrollRight = lastX2 - lastX1 > 0
//            isScrollLeft = lastX2 - lastX1 < 0
//        }
//
//        if (isScrollRight)
//            println("----------------右滑")
//        if (isScrollLeft){
//            println("----------------左滑")
//        }
//    }
//
//}
//
//
//class MyLinearLayoutManager(
//    context: Context, @RecyclerView.Orientation orientation: Int,
//    reverseLayout: Boolean
//) : LinearLayoutManager(context, orientation, reverseLayout) {
//
//    var canScrollable = true
//
//    override fun canScrollHorizontally(): Boolean {
//
//        return canScrollable
//    }
//
//}
//
//class MyPagerSnapHelper : PagerSnapHelper() {
//    override fun onFling(velocityX: Int, velocityY: Int): Boolean {
//        Log.e("Snaphelper","fling-----------------")
//        return super.onFling(velocityX, velocityY)
//    }
//
//    var canScrollable = true
//
//
//    override fun findTargetSnapPosition(
//        layoutManager: RecyclerView.LayoutManager?,
//        velocityX: Int,
//        velocityY: Int
//    ): Int {
//        println("velocityX----------------=====$velocityX")
//        return super.findTargetSnapPosition(layoutManager, velocityX, velocityY)
//    }
//}
//
//
//class ItemAdapter(val datas: List<String>) : RecyclerView.Adapter<ItemAdapter.VH>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
//        return VH(View.inflate(parent.context, R.layout.item_tv, null))
//    }
//
//    override fun getItemCount(): Int {
//        return datas.size
//    }
//
//    override fun onBindViewHolder(holder: VH, position: Int) {
//
//        holder.tv.text = "${datas[position]}"
//        holder.tv.layoutParams.width = APP.instance.resources.displayMetrics.widthPixels
//        holder.tv.layoutParams.height = APP.instance.resources.displayMetrics.heightPixels
//        holder.itemView.setBackgroundColor(Color.rgb(Random().nextInt(255),Random().nextInt(255),Random().nextInt(255)))
//
//
//    }
//
//    class VH(v: View) : BaseViewHolder(v) {
//        var tv: TextView = getView(R.id.tvContent)
//    }
//
//}
//
//
//class PageAdapter(val act: FragmentActivity, val fragments: List<Fragment>) :
//    FragmentStateAdapter(act) {
//
//    override fun getItemCount(): Int {
//        return fragments.size
//    }
//
//    override fun createFragment(position: Int): Fragment {
//        return fragments[position]
//    }
}