package com.example.jrhapplication

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/8/5     3:52 PM
 * 用途:
 ***************************************
 */


//import kotlinx.coroutines.runBlocking

//fun main() = runBlocking {
//    val startTime = System.currentTimeMillis()
//    val job = launch(Dispatchers.Default) {
//        var nextPrintTime = startTime
//        var i = 0
//        while (isActive) { // computation loop, just wastes CPU
//            // print a message twice a second
//            if (System.currentTimeMillis() >= nextPrintTime) {
//                println("job: I'm sleeping ${i++} ...")
//                nextPrintTime += 500L
//            }
//        }
//    }
//    delay(1300L) // delay a bit
//    println("main: I'm tired of waiting!")
//    job.cancelAndJoin() // cancels the job and waits for its completion
//    println("main: Now I can quit.")
//}


//fun main() = runBlocking {
//    val job = launch {
//        try {
//            repeat(1000) { i ->
//                println("job: I'm sleeping $i ...")
//                delay(500L)
//            }
//        } finally {
//            println("job: I'm running finally")
//        }
//    }
//    delay(1300L) // delay a bit
//    println("main: I'm tired of waiting!")
//    job.cancelAndJoin() // cancels the job and waits for its completion
//    println("main: Now I can quit.")
//}

//fun main() = runBlocking {
//    val job = launch {
//        try {
//            repeat(1000) { i ->
//                println("job: I'm sleeping $i ...")
//                delay(500L)
//            }
//        } finally {
//            withContext(NonCancellable) {
//                println("job: I'm running finally")
//                delay(1000L)
//                println("job: And I've just delayed for 1 sec because I'm non-cancellable")
//            }
//        }
//    }
//    delay(1300L) // delay a bit
//    println("main: I'm tired of waiting!")
//    job.cancelAndJoin() // cancels the job and waits for its completion
//    println("main: Now I can quit.")
//}

//fun main() = runBlocking {
//    try {
//        withTimeout(3000){
//            repeat(Int.MAX_VALUE)
//            {
//                print("$it------")
//                delay(100)
//            }
//        }
//    }catch (e:Exception){
//        e.printStackTrace()
//        println("exception------------$e")
//    }
//
//}

//fun main() = runBlocking<Unit> {
//    val time = measureTimeMillis {
//        val one = doSomethingUsefulOne()
//        val two =  doSomethingUsefulTwo()
//        println("The answer is ${one + two}")
//    }
//    println("Completed in $time ms")
//}
//
//suspend fun doSomethingUsefulOne(): Int {
//    delay(1000L) // pretend we are doing something useful here
//    return 13
//}
//
//suspend fun doSomethingUsefulTwo(): Int {
//    delay(1000L) // pretend we are doing something useful here, too
//    return 29
//}

//fun main() = runBlocking {
////    flowOf("A", "B", "C")
////        .onEach {
////            println("1$it")
////        }
////        .buffer(2)
////        .collect {
////            delay(1000)
////            println("2$it") }
////
////    flow {
////        repeat(10_000) {
////            emit(it)
////        }
////    }.onEach { println("a$it") }
////        .buffer(1000)
////        .collect {
////            delay(1000)
////            println("b$it")
////        }
//
//    flow {
//        repeat(100) {
//            delay(100)
//            emit(it)
//        }
//    }.conflate().onEach { delay(1000) }.collect{
//        println("a$it")
//    }
//
//}

//
//fun main() = runBlocking {
//    val send = MutableLiveData<Int>()
//    repeat(100){
//        send.value= it
//    }
//
//}

class User(val name: String)


///**
// * kotlin 编译器可以 类型推断 小心有坑
// */
//fun main() {
//    //Array<String>
//    val listOfParams = arrayOf("android", "kotlin")
//    listOfParams.forEach {
//
//    }
//
//    //Array<User>
//    val listOfUsers = arrayOf(User("android"), User("kotlin"))
//    listOfUsers.forEach {
//
//    }
//
//    //Array<Any> 这里 arrayof 加入 User 和 "android" string---->Any
//    val listOfUserParams = arrayOf(User("android"), User("kotlin"),"android")
//
//    listOfUserParams.forEach {
//
//    }
//
//    // 必要时强制声明数组类型 比如这里指定是User数组，只能传User ,传string类型将会出现编译问题
//    val listOnlyUser:Array<User> = arrayOf(User("android"),User("kotlin"),"android")
//
//}



//fun main() = runBlocking<Unit> {
////    val nums = (1..5).asFlow()// numbers 1..3 every 300 ms
////    val strs = (2.. 4).asFlow()// strings every 400 ms
////    nums.zip(strs) { a, b -> "$a -> $b" } // compose a single string with "zip"
////        .collect { value -> // collect and print
////            println("$value  ms from start")
////        }
////
////    val a = null
////    val ss =  a ?: 2
//
////    val list = mutableListOf<String>("a","b","c")
////    list[0] = "d"
////    println(list)
//
//
//
//}


fun main() {
    val aList = mutableListOf(User("android"), User("kotlin"))
    val bList = mutableListOf(User("kotlin"),User("android"))

    println("a=b=${aList == bList}")
}

//fun main() = runBlocking<Unit> {
//    val nums = (1..3).asFlow().onEach { delay(300) } // numbers 1..3 every 300 ms
//    val strs = flowOf("one", "two", "three").onEach { delay(400) } // strings every 400 ms
//    val startTime = System.currentTimeMillis() // remember the start time
//    nums.combine(strs) { a, b -> "$a -> $b" } // compose a single string with "combine"
//        .collect { value -> // collect and print
//            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
//        }
//}