package com.example.proxy

import com.example.proxy.DelegateParamsUtil.addCommonParams
import com.example.proxy.DelegateParamsUtil.doIntercept
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy

/**
 * 代理工厂 用来创建代理对象
 */
object DelegateFactory {

    fun createClass(delegate: IDelegate): IDelegate? {
        return Proxy.newProxyInstance(delegate.javaClass.classLoader, delegate.javaClass.interfaces,
            InvocationHandler { proxy, method, args ->
                val methodName = method.name
                if (methodName == "handlePage") {
                    val isIntercept = doIntercept(
                        (args[0] as String),
                        (args[1] as String)
                    )
                    val p = addCommonParams(
                        (args[0] as String),
                        (args[1] as String)
                    )
                    val newArgs = arrayOf(args[0] as String, p)
                    if (!isIntercept) return@InvocationHandler method.invoke(delegate, *newArgs)
                }
                null
            }) as? IDelegate
    }

}
