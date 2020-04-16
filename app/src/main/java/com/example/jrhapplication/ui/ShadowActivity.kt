package com.example.jrhapplication.ui

import android.graphics.Outline
import android.graphics.Path
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.RequiresApi
import com.example.jrhapplication.R
import com.jrhlive.library.dp2px
import kotlinx.android.synthetic.main.activity_shadow.*

class ShadowActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shadow)

        s1.outlineProvider = object :ViewOutlineProvider(){
            override fun getOutline(view: View?, outline: Outline?) {
               outline?.let {
                   it.setRoundRect(0,0,s1.width,s1.height,dp2px(10).toFloat())
               }
            }
        }
        s1.clipToOutline = true

        s2.outlineProvider = object :ViewOutlineProvider(){
            override fun getOutline(view: View?, outline: Outline?) {
                outline?.let {
                    it.setRoundRect(dp2px(50),dp2px(50),s1.width,s1.height,dp2px(10).toFloat())
                }
            }
        }

        s2.clipToOutline = true

        s3.outlineProvider = object :ViewOutlineProvider(){
            override fun getOutline(view: View?, outline: Outline?) {
                outline?.let {
                    it.setOval(dp2px(0),dp2px(0),s1.width,s1.height)
                }
            }
        }

        s3.clipToOutline = true

        s4.post {
            s4.outlineProvider = object :ViewOutlineProvider(){
                override fun getOutline(view: View?, outline: Outline?) {
                    outline?.let {

                        var p = Path()
                        p.lineTo(dp2px(20).toFloat(),dp2px(20).toFloat())
                        p.lineTo(dp2px(20).toFloat(),dp2px(80).toFloat())
                        p.lineTo(dp2px(80).toFloat(),dp2px(80).toFloat())
                        p.close()

                        it.setConvexPath(p)
                    }
                }
            }

            s4.clipToOutline = true
        }



    }
}
