package com.example.proxy


/**
 * 代理类
 * @property realDelegate RealDelegate
 */
class ProxyDelegate:IDelegate {

    private val realDelegate by lazy { RealDelegate() }

    override fun handlePage(page: String, params: String) {
        //增加拦截器
        val isIntercept = DelegateParamsUtil.doIntercept(page,params)
        //扩展新增参数
        val realParams  = DelegateParamsUtil.addCommonParams(page,params)
        if (!isIntercept)
        realDelegate.handlePage(page, realParams)
    }

}