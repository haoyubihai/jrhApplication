package com.example.jrhapplication

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.core.ImageTranscoderType
import com.facebook.imagepipeline.core.MemoryChunkType
import com.jrhlive.library.dp2px

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/4/3     1:25 PM
 * 用途:
 ***************************************
 */

class APP :Application(){

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this, ImagePipelineConfig.newBuilder(applicationContext)
        .setMemoryChunkType(MemoryChunkType.BUFFER_MEMORY)
            .setImageTranscoderType(ImageTranscoderType.JAVA_TRANSCODER)
            .experiment().setNativeCodeDisabled(true)
            .build())

        instance = this

        registerLifecycle()

        /**
         * 新增一种检测 app 前后台的方法 ，这种方法其实内部也是通过注册一个全局的监测activity生命周期的回调，
         * 并通过计数，前台++ 后台--来判断，与我们通常注册监听的方式没啥差别
         * 还需要单独引入 lifecycle process 使用
         */
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationLifecycleObserver())

//        Fresco.initialize(this)
    }



    private fun initListener(it: View?) {
        TODO("Not yet implemented")
    }

    private fun initLayoutParams(): FrameLayout.LayoutParams {
        return FrameLayout.LayoutParams(dp2px(100),dp2px(100))
    }

    private fun registerLifecycle() {
        instance.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks{
            /**
             * Called when the Activity calls [super.onCreate()][Activity.onCreate].
             */
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

            }

            /**
             * Called when the Activity calls [super.onStart()][Activity.onStart].
             */
            override fun onActivityStarted(activity: Activity) {
//               checkAndAddFloatView(activity)
            }

            /**
             * Called when the Activity calls [super.onResume()][Activity.onResume].
             */
            override fun onActivityResumed(activity: Activity) {
            }

            /**
             * Called when the Activity calls [super.onPause()][Activity.onPause].
             */
            override fun onActivityPaused(activity: Activity) {
            }

            /**
             * Called when the Activity calls [super.onStop()][Activity.onStop].
             */
            override fun onActivityStopped(activity: Activity) {
            }

            /**
             * Called when the Activity calls
             * [super.onSaveInstanceState()][Activity.onSaveInstanceState].
             */
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            /**
             * Called when the Activity calls [super.onDestroy()][Activity.onDestroy].
             */
            override fun onActivityDestroyed(activity: Activity) {
            }

        })
    }

    private fun checkAndAddFloatView(activity: Activity) {

        val root = activity.window.decorView as FrameLayout
        val floatView = root.findViewById<ViewGroup>(R.id.floatV)
        if (floatView==null){
            //没有添加过
            val v = LayoutInflater.from(activity).inflate(R.layout.lay_float,null,false)
            val params = FrameLayout.LayoutParams(dp2px(100),dp2px(100))
            root.addView(v,params)
        }

    }

    companion object{
        lateinit var instance :Application

    }
}


internal class ApplicationLifecycleObserver:LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onForeground(){
        println("-------onForeground")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onBackground(){
        println("-------onBackground")

    }
}

