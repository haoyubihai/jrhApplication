package com.example.proxy


fun main() {

    staticDelegate()

    autoDelegate()

    cgDelegate()

}

/**
 * 静态代理
 */
private fun staticDelegate() {
    println("------------------静态代理-----------start--------------------------------")
    val delegate = ProxyDelegate()
    delegate.handlePage("login", "a=1,b=0")
    delegate.handlePage("home", "1")
    println("------------------静态代理-----------end--------------------------------\n\n\n\n")
}

/**
 * jdk 动态代理
 */
private fun autoDelegate() {
    println("------------------jdk动态代理-----------start--------------------------------")
    val delegate = DelegateFactory.createClass(RealDelegate())
    delegate?.handlePage("login", "a=1,b=0")
    delegate?.handlePage("home", "1")
    println("------------------jdk动态代理-----------end--------------------------------\n\n\n\n")
}

/**
 * 使用cglib
 */
private fun cgDelegate() {
    println("------------------cglib动态代理-----------start--------------------------------")
    CgLibDelegate.getDelegate<NewDelegate>()?.run {
        handlePage("login", "a=1,b=0")
        visit()
        order()
    }
    println("------------------cglib动态代理-----------end--------------------------------\n\n\n\n")

}

