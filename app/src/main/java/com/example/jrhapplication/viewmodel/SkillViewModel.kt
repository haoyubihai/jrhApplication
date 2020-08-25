package com.example.jrhapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.jrhapplication.bean.PersonUser
import com.example.jrhapplication.bean.User
import kotlin.random.Random

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/8/24     10:18 AM
 * 用途:
 ***************************************
 */
class SkillViewModel:ViewModel() {

    /**
     * 创建一个 size=1000 的user的集合 userList
     */
    val userList = List(100){
        User(it.toLong(),"name$it", Random(it).nextInt(),"address${it%3}")
    }

    val personList = List(100){
        PersonUser("name$it",it)
    }

    //----------------------------------------------associateBy-----------------------------------------------------------

    /**
     * user-> id = key
     */
    val userListToMapById = userList.associateBy { it.id }

    /**
     * user -> name = key
     */
    val userListToMapByName = userList.associateBy { it.name }

    /**
     * key值不唯一
     */
    val userListToMapByAddress  = userList.associateBy { it.address }

    /**
     * user -> id =key  name = value
     */
    val userIdKeyNameValueList = userList.associateBy (keySelector = {it.id},valueTransform = {it.name})

    //----------------------------------------------groupBy-----------------------------------------------------------

    /**
     * user -> address 分组
     */
    val userListToMapGroupByAddress = userList.groupBy { it.address }

    /**
     * 以user-> address 分组 存储到指定的集合
     */
    val userAddress = LinkedHashMap<String,MutableList<User>>()
    val toUserAddress = userList.groupByTo(userAddress,  { it.address })

    //-------------------------first,firstOrNull,last,lastOrNull,find,map,filter-----------------------------------------------------------

    /**
     * userList为empty 抛出异常
     */
    val firstUser = userList.first()

    /**
     * 推荐  userList为empty 抛出异常
     */
    val firstUserNull = userList.firstOrNull()

    /**
     * last
     */
    val lastUser = userList.last()
    val lastUserNull = userList.lastOrNull()

    val userFind = userList.find { it.age>100 }

    fun testFirstUser():User= userList.toMutableList().run {
            clear()
            first()
        }

    /**
     *  返回 user? 空安全校验
     */
    fun testFirstNullUser():User? = userList.toMutableList().run {
        clear()
        firstOrNull()
    }

    /**
     * 获取name的list
     */
    val userNames = userList.map { it.name }

    /**
     * List<User>--> List<PersonUser>
     */
    val personUsers = userList.map { PersonUser(it.name) }

    val userFilter  = userList.filter { it.age>100 }

    val userFilterNot = userList.filterNot {it.age%2==0  }

    val userTake = userList.take(10)
    val userTakeLast = userList.takeLast(10)

    /**
     * 所有user age 为偶数的集合
     */
    val userTakeWhile = userList.takeWhile { it.age%2==0 }

    /**
     * 所有 user 的age>100 返回该 list  否则返回 null
     */
    val userTakeIf = userList.takeIf { it.all { user-> user.age>100 } }

    /**
     * 所有 age>0 返回true
     */
    val userAll = userList.all { it.age>0 }

    /**
     * 只要有一个 age>0 符合条件 返回true
     */
    val userAny = userList.any { it.age>0 }

    val userPersonsSet = userList.union(personList)




}


