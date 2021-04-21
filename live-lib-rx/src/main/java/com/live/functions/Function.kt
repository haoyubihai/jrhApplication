package com.live.functions

interface Function< in T, out R> {

    fun apply(t:T):R
}