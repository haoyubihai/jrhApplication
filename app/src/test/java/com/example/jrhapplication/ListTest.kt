package com.example.jrhapplication

import com.example.jrhapplication.bean.PersonUser
import com.example.jrhapplication.bean.User
import com.example.jrhapplication.viewmodel.SkillViewModel
import kotlin.random.Random

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/8/24     11:30 AM
 * 用途:
 ***************************************
 */

fun main() {

    val listTest = ListTest()

    listTest.userList.forEach {
        println("--------------${it}")
    }

}

class ListTest {

    /**
     * 创建一个 size=1000 的user的集合 userList
     */
    val userList = List(20) {
        User(it.toLong(), "name$it", it, "address${it % 3}")
    }



    val userList2 = List(10){
        User(it.toLong(),"name$it", it,"address${it%3}")
    }

    val personList = List(100){
        PersonUser("name$it",it)

    }



    /**
     *  if (index >= 0 && index <= lastIndex) get(index) else defaultValue(index)
     *
     */
    val userGetOrElse = userList.getOrElse(1){index->
        println("do something $index no find")
    }

    /**
     * 推荐 *****
     * if (index >= 0 && index <= lastIndex) get(index) else null
     */
    val userGetOrNull = userList.getOrNull(1)

    /**
     * 所有 age>0 返回true
     */
    val userAll = userList.all { it.age>0 }

    /**
     * 只要有一个 age>0 符合条件 返回true
     */
    val userAny = userList.any { it.age>0 }

    /**
     * 推荐  userList为empty 抛出异常
     */
    val firstUserNull = userList.firstOrNull()

    val lastUserNull = userList.lastOrNull()

    /**
     * 获取 第一个 年龄>100 是偶数的 下标
     */
    val userIndexOfFirst = userList.indexOfFirst { it.age>100 && it.age%2==0 }

    val userIndexOfLast = userList.indexOfLast { it.age>100 && it.age%2==0 }

    /**
     * 第一个年龄大于100的 user
     */
    val userFist = userList.first { it.age>100 }

    val userLast = userList.last { it.age>100 }

    /**
     * 升序
     */
    val userSort = userList.sortedBy { it.age }


    val userNone = userList.none { it.age==19 }

    /**
     * 随机一个 user ?
     */
    val userRandom = userList.randomOrNull()



    /**
     * 丢弃前 5个 元素
     */
    val userDrop = userList.drop(5)

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
     * last
     */
    val lastUser = userList.last()



    val userFind = userList.find { it.age>100 }

    fun testFirstUser(): User = userList.toMutableList().run {
        clear()
        first()
    }

    /**
     *  返回 user? 空安全校验
     */
    fun testFirstNullUser(): User? = userList.toMutableList().run {
        clear()
        firstOrNull()
    }

    /**
     * 获取name? 的list
     */
    val userNames = userList.map { it.name }
    /**
     * 获取name 的list
     */
    val userNamesNotNull = userList.mapNotNull { it.name }


    /**
     * List<User>--> List<PersonUser>
     */
    val personUsers = userList.map { PersonUser(it.name) }

    /**
     * mapto 存储到指定的集合
     */
    val personUserMapto = userList.mapTo(mutableListOf()){PersonUser(it.name)}

    val userFilter  = userList.filter { it.age>100 }

    val userFilterNot = userList.filterNot {it.age%2==0  }

    /**
     *  userList 前10个
     */
    val userTake = userList.take(10)

    /**
     * userList 最后10个
     */
    val userTakeLast = userList.takeLast(10)

    /**
     * userList --> 11-20的元素
     */
    val userSubList = userList.subList(11,20)

    /**
     * 所有user age 为偶数的集合
     */
    val userTakeWhile = userList.takeWhile { it.age%2==0 }

    /**
     * 所有 user 的age>100 返回该 list  否则返回 null
     */
    val userTakeIf = userList.takeIf { it.all { user-> user.age>100 } }



    /**
     * union 取两个集合的并集 userList ∪ userList2
     */
    val userUnion = userList.union(userList2)

    /**
     * subtract 取两个集合的交集 userList ∩ userList2
     */
    val userIntersect = userList.intersect(userList2)

    /**
     * subtract userList取 userList2 的补集
     */
    val userSubtract = userList.subtract(userList2)

    /**
     * userList重复元素，只保留一个
     */
    val userDistinct = userList.distinct()


}
