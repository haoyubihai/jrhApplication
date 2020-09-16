package com.example.jrhapplication

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/9/8     6:19 PM
 * 用途:
 ***************************************
 */


fun main() {

   val str = "  我们    是 中国人"
    println(str)
    println(str.deleteSpaces())

}

private fun String.deleteSpaces(): String {
    return this.replace(" ", "")
}

class Skill {

}