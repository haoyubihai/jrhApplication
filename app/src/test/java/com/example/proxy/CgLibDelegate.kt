package com.example.proxy

import net.sf.cglib.proxy.Enhancer
import net.sf.cglib.proxy.MethodInterceptor
import net.sf.cglib.proxy.MethodProxy
import java.lang.reflect.Method

object CgLibDelegate {

    inline fun <reified T> getDelegate(): T? {
        return Enhancer().apply {
            setSuperclass(T::class.java)
            setCallback(CgMethodInterceptor())
        }.create() as? T
    }
}

class CgMethodInterceptor : MethodInterceptor {
    override fun intercept(
        obj: Any?,
        method: Method?,
        args: Array<out Any>?,
        proxy: MethodProxy?
    ): Any? {
        println("methodName=${method?.name}---------做一些增加服务--拦截服务")
        return proxy?.invokeSuper(obj, args.orEmpty())
    }

}
