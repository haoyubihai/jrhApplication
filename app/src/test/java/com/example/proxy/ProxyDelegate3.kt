package com.example.proxy

/**
 * 代理类 kotlin  by 关键字的使用
 *  新增方法 eg
 * @property realDelegate RealDelegate
 */
class ProxyDelegate3(private val delegate:IDelegate):IDelegate by delegate

class ProxyDelegate4:IDelegate by RealDelegate()