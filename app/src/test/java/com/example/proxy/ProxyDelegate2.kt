package com.example.proxy

/**
 * 代理类 使用 kotlin 关键字 对需要处理的方法重写
 * @property realDelegate RealDelegate
 */
class ProxyDelegate2(private val delegate:IDelegate):IDelegate by delegate {


    override fun handlePage(page: String, params: String) {
        //增加拦截器
        val isIntercept = doIntercept(page,params)
        //扩展新增参数
        val realParams  = addCommonParams(page,params)
        if (!isIntercept)
        delegate.handlePage(page, realParams)
    }

    private fun addCommonParams(page: String, params: String) :String{
        return if (page=="login") "$params userName = admin" else params
    }

    private fun doIntercept(page: String, params: String): Boolean {
        //参数为空进行拦截
        return params.isEmpty()
    }
}