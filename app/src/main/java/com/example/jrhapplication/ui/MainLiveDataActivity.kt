package com.example.jrhapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.jrhapplication.R
import kotlinx.android.synthetic.main.activity_main_live_data.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
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
                repeat(1000){

                    sendData.postValue(it)
                }
            }
        }
        btnSetRequest.setOnClickListener {
            lifecycleScope.launch {
                repeat(1000){

                    sendData.value=it
                }
            }
        }



        sendData.observe(this, Observer {
            lifecycleScope.launch {
                tvData.text = it.toString()
                println("-----$it")
            }
        })



        val f = MutableStateFlow(0)

        postFlow.setOnClickListener {

           lifecycleScope.launch {

//               flow {
//                   repeat(1000){
//                       emit(it)
//
//                   }
//               }.collect {
//                   tvData.text = it.toString()
//                   println("-----$it")
//               }

               repeat(1000){
                   f.value =it
               }
           }
        }

        lifecycleScope.launch{
            f.onEach {
                println("---s--$it")
            }.collect {
                tvData.text = it.toString()
                println("-----$it")
            }
        }
//        sendData.observe(this, Observer {
//            tvData.text = it.toString()
//            println("------$it")
//        })

        postSend.setOnClickListener{

            /**
             *
             *这种情况下 ，先接受到1000，再接收到999
             *
             * postValue 内部，先通过一个mPendingData 中间变量缓存该value ，随后通过handler post一个runnable,
             * 切换到主线程，再代用setValue的方式 发送该value，通过一种生产者消费者模式，事件消费后，才能继续发送后续事件。
             * 一段时间内多次调用postValue只会收到最后一次的值
             * 线程切换会带来消耗，假如每次都post一个runnable的意义不大
             *
             * 内部通过加锁的方式，解决多线程安全问题
             */
            sendData.postValue(999)
            sendData.value = 1000


        }




    }



}