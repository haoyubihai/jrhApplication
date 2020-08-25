package com.example.jrhapplication.bean

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/8/24     10:18 AM
 * 用途:
 ***************************************
 */
data class User(val id:Long,val name:String?,val age:Int,val address:String)

data class PersonUser(val name: String?,val age: Int=0)