package com.example.proxy

import net.sf.cglib.proxy.Enhancer
import net.sf.cglib.proxy.MethodInterceptor
import net.sf.cglib.proxy.MethodProxy
import java.lang.reflect.Method


fun main() {

//    staticDelegate()

//    autoDelegate()

      cgDelegate()

}

/**
 * 静态代理
 */
private fun staticDelegate() {
    val delegate = ProxyDelegate()
    delegate.handlePage("login", "a=1,b=0")
    delegate.handlePage("home", "1")
}

/**
 * jdk 动态代理
 */
private fun autoDelegate() {

    val delegate =DelegateFactory.createClass(RealDelegate())
    delegate?.handlePage("login", "a=1,b=0")
    delegate?.handlePage("home", "")
}

/**
 * 使用cglib
 */
private fun cgDelegate() {
    CgLibDelegate.getDelegate<NewDelegate>()?.run {
        handlePage("login","a=1,b=0")
        visit()
        order()
    }
}

