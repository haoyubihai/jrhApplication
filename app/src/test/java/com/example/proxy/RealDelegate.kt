package com.example.proxy

/**
 * 真实实现类
 */
open class RealDelegate:IDelegate {
    override fun handlePage(page: String, params: String) {
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
}