package com.example.jrhapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.jrhapplication.R
import com.example.jrhapplication.viewmodel.TimerVMImpl
import com.example.jrhapplication.viewmodel.TimerViewModel
import com.example.jrhapplication.viewmodel.TimerViewModelImpl
import kotlinx.android.synthetic.main.activity_material_shape_drawable.*
import kotlinx.android.synthetic.main.activity_timer.*
import java.util.*

class TimerActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        val timer = ViewModelProvider(this).get(TimerVMImpl::class.java)
        btn1.setOnClickListener {
            t1.text="0"
            timer.startTimer("1",initDelayTime = 0,totalTime = 10000,intervalTime = 1000,completeAction = {
                t1.text="over"
            },intervalAction = {_,time->
                t1.text="${time/1000}"
            })
        }

        btn2.setOnClickListener {
            timer.startTimer("2",initDelayTime = 0,totalTime = 15000,intervalTime = 200,completeAction = {
                t2.text="over"
            },intervalAction = {_,time->
                t2.text="${time/1000}"
            })
        }
        btn3.setOnClickListener { timer.stopAllTimer() }

        btn4.setOnClickListener { Toast.makeText(this,"hello btn4",Toast.LENGTH_SHORT).show() }
        btn5.setOnClickListener { btn4.isClickable = true }
        btn6.setOnClickListener { btn4.isClickable = false }

        btn7.setOnClickListener { timer.timeDown(3){
            println("$it s over")
        } }

    }
}