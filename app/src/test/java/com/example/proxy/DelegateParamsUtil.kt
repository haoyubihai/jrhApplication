package com.example.proxy


object DelegateParamsUtil {

     fun addCommonParams(page: String, params: String) :String{
        return if (page=="login") "$params userName = admin" else params
    }

     fun doIntercept(page: String, params: String): Boolean {
        //参数为空进行拦截
        return params.isEmpty()
    }
}