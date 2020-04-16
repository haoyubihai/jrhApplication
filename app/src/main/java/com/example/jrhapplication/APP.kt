package com.example.jrhapplication

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.core.ImageTranscoderType
import com.facebook.imagepipeline.core.MemoryChunkType

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

//        Fresco.initialize(this)
    }
}