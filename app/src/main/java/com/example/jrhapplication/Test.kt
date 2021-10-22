package com.example.jrhapplication

import android.content.SharedPreferences

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/3/6     3:46 PM
 * 用途:
 ***************************************
 */


fun SharedPreferences.open(block:SharedPreferences.Editor.()->Unit){
    val editor = edit()
    block(editor)
    editor.apply()
}


fun SharedPreferences.open2(block:(editor:SharedPreferences.Editor)->Unit){
    val editor = edit()
    block(editor)
    editor.apply()
}


fun open3(sharedpreferences:SharedPreferences,block:(editor:SharedPreferences.Editor)->Unit){
    block(sharedpreferences.edit())
}


class Person{

    interface Son
    fun create():Son = object :Son{}
}


fun Person.say(block:Person.Son.()->Unit){

    val son = create()
    block(son)
}

fun Person.say2(block:(son: Person.Son)->Unit){
    val son = create()
    block(son)
}

fun say3(person:Person, block:(Person.Son)->Unit){
    val son = person.create()
    block(son)
}


fun Person.say4(block:Person.Son.(String)->Unit){

    val son = create()
    block(son,"name")
}

fun Person.say5(block:Person.Son.(name:String)->Unit){

    val son = create()
    block(son,"name")
}
