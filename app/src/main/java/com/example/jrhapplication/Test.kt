package com.example.jrhapplication

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/3/6     3:46 PM
 * 用途:
 ***************************************
 */

open class Person(id:Int){

    constructor(id: Int,name:String):this(id){
        println("person---id=$id---name=$name")
    }
}
class Boy:Person{

    constructor(id: Int):super(id){

    }
    constructor(id: Int,name:String):super(id,name){

        println("boy---id=$id---name=$name")

    }


}
class Test{
    companion object{
        fun main() {
            println(Boy(20,"jack"))
        }
    }

}



