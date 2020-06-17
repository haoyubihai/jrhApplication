package com.example.jrhapplication.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jrhapplication.R
import com.example.jrhapplication.widgets.DisplayUtil
import com.jrhlive.library.dp2px
import kotlinx.android.synthetic.main.motion_activity.*

class MotionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.motion_activity)
        cp.run {
            height = DisplayUtil.dip2px(this@MotionActivity,20f)
            progress=60
            secondProgress=80
        }

        maskView.post {
            maskView.initViews()
            maskView.startAni()
        }


        moveProgress.post {

            moveProgress.initViews(dp2px(15).toFloat(), Color.RED,Color.BLUE)
            moveProgress.startAni(6000)
        }



    }
}