package com.example.jrhapplication.ktx

import android.app.Activity
import android.content.Intent
import kotlin.reflect.KClass

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/3/15     7:40 PM
 * 用途:
 ***************************************
 */

inline fun <reified T:Activity> Activity.startKtxActivity(){
    startActivity(Intent(this,T::class.java))
}