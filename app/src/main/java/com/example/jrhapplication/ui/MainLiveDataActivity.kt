package com.example.jrhapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.jrhapplication.R
import kotlinx.android.synthetic.main.activity_main_live_data.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

class MainLiveDataActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_live_data)

        val sendData = MutableLiveData<Int>()

        btnRequest.setOnClickListener {
            lifecycleScope.launch {
                repeat(100){

                    sendData.value=it
                }
            }
        }

        sendData.observe(this, Observer {
            lifecycleScope.launch {
                delay(1000)
                println("-----$it")
            }
        })

    }
}