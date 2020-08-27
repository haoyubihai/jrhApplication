package com.example.jrhapplication

import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/8/26     11:06 AM
 * 用途:
 * 场景1：为线下作业标题 追加当前状态
 * 场景2：当线下作业说明为空时，添加默认提示语
 ***************************************
 */

fun main() {

    val homework = OfflineHomework()
    homework.title = "线下作业"


    val address:String by lazy { "a" }
    /**
     * 代理 当name值改变时，进行回调监听
     */
    var name :String by Delegates.observable(""){ property, oldValue, newValue ->
        println("-----propertyName=${property.name}----oldValue=$oldValue---newValue=$newValue")
    }

    /**
     * 设置监听 ，对设置的newValue进行拦截 true 可以设置新值，false 不可以设置新值
     */
    var age:Int by Delegates.vetoable(0){property, oldValue, newValue ->
        newValue>oldValue
    }
    
    name = "hello"
    name = "world"

    age =20
    println("age------$age")
    age = 10
    println("age------$age")
    age = 30
    println("age------$age")

//    println(homework.title)

    val map = mutableMapOf<String,Any?>("title" to "android","price" to 30.0)
    Course(map).run {
        println(title)
        println(price)
    }
}

class HomeworkDelegate :ReadWriteProperty<OfflineHomework,String>{

    var mValue: String? = null
    val mState = "未批改"

    override fun setValue(thisRef: OfflineHomework, property: KProperty<*>, value: String) {
        this.mValue = "来看看今天的$value"
        property.name
    }

    override fun getValue(thisRef: OfflineHomework, property: KProperty<*>): String {
        return "[$mState]-$mValue"
    }

}

class OfflineHomework {
    var title: String by HomeworkDelegate()
}

data class Course(val map:Map<String,Any?>){
    val title:String? by map
    val price :Double by map
}

