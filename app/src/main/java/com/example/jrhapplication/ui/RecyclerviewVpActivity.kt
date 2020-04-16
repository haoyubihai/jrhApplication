package com.example.jrhapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.SnapHelper
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.jrhapplication.R
import com.example.jrhapplication.ui.fragment.vpFragment
import kotlinx.android.synthetic.main.activity_recyclerview_vp.*

/**
 * 用recyclerView实现ViewPager效果
 */
class RecyclerviewVpActivity : AppCompatActivity() {


    var isScrollAble = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_vp)

        val fragments = mutableListOf<Fragment>()
        repeat(5){
            fragments.add(vpFragment.newInstance("$it","$it"))
        }

        val adapter = PageAdapter(this,fragments)
        vp.adapter = adapter


        btnNoScroll.setOnClickListener {
            isScrollAble = !isScrollAble
            vp.isUserInputEnabled = isScrollAble
        }
        btnNext.setOnClickListener {
            vp.beginFakeDrag()
            vp.fakeDragBy(-300f)
            vp.endFakeDrag()

        }
        btnPre.setOnClickListener {
            vp.beginFakeDrag()
            vp.fakeDragBy(300f)
            vp.endFakeDrag()
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
           MotionEvent.ACTION_MOVE->{
               Log.e("--------","rawX=${event.rawX}----X=${event.x}----vpWidth--${vp.width.toFloat()}")
               vp.endFakeDrag()
           }
        }
        return super.onTouchEvent(event)
    }
}




class PageAdapter(val act:FragmentActivity,val fragments:List<Fragment>) :
    FragmentStateAdapter(act){

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}