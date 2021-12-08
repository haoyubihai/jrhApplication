package com.example.proxy

/**
 * 必须 open 不能是 final  底层是通过继承实现
 */
open class NewDelegate {

    open fun handlePage(page: String, params: String) {
        when (page) {
            "login" ->{
                println("跳转到LoginActivity---params=${params}")
            }
            "home" ->{
                println("跳转到HomeActivity---params=${params}")
            }
            else -> {
                println("暂不支持")
            }
        }
    }

    fun visit(){
        println("visit----------------")
    }

    open fun order(){
        println("order----------------")
    }
}