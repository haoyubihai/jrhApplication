package com.example.jrhapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.jrhapplication.R
import com.facebook.rebound.*
import com.jrhlive.library.dp2px
import kotlinx.android.synthetic.main.activity_spring.*

class SpringActivity : BaseActivity() {

    var bouncinessValue = 30.0
    var speed = 10.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spring)

        ivScale.setOnClickListener {
            doScale()
        }

        ivTrans.setOnClickListener {
            doTrans()
        }

        initView()
    }

    private fun initView() {
        seekBarBounciness.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                bouncinessValue = progress.toDouble()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        seekBarSpeed.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                speed = progress.toDouble()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

    }

    private fun doScale() {

        val springSystem = SpringSystem.create()
        springSystem.addListener(object :SpringSystemListener{
            override fun onBeforeIntegrate(springSystem: BaseSpringSystem?) {
//                TODO("Not yet implemented")
            }

            override fun onAfterIntegrate(springSystem: BaseSpringSystem?) {
//                TODO("Not yet implemented")
            }
        })
        val spring = springSystem.createSpring()
        spring.apply {
            springConfig = SpringConfig.fromBouncinessAndSpeed(bouncinessValue,speed)
            addListener(object :SimpleSpringListener(){
                override fun onSpringUpdate(spring: Spring?) {
                    spring?.let {
                        val value = spring.currentValue
                        println("value------------------====$value")
                        val scale  = 1f-value*0.5f
                        ivScale.scaleX = scale.toFloat()
                        ivScale.scaleY = scale.toFloat()

                    }
                }
            })
        }
        spring.endValue = 1.0
    }

    private fun doTrans(){
        SpringSystem.create().createSpring().apply {
            springConfig = SpringConfig.fromOrigamiTensionAndFriction(bouncinessValue,speed)
            addListener(object :SimpleSpringListener(){
                override fun onSpringUpdate(spring: Spring?) {
                    spring?.let {
                        val value = spring.currentValue
                        println("value------------------====$value")

                        val x = dp2px(300)*value
                        val y = dp2px(300)*(1-value*value)
                        ivTrans.translationX = x.toFloat()
                        ivTrans.translationY = y.toFloat()
                    }
                }
            })
            endValue = 1.0
        }
    }
}


