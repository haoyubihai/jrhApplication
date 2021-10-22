package com.example.jrhapplication

class TestBlock {

    /**
     * 函数类型可以有一个额外的接收者类型，它在表示法中的点之前指定类型： A.(B) -> C 表示可以在 A 的接收者对象上以一个 B 类型参数来调用并返回一个 C 类型值的函数。
     * 带有接收者的函数字面值通常与这些类型一起使用。
     *
     * 这么理解：
     *
     * action 是一个函数类型的参数，该函数又指定了一个接收者类型，就是CourseTimer
     * 1:这里相当于直接给CourseTimer 新增了一个action 方法，可以用CourseTimer的实例去调用该action方法，如 courseTimer.action()
     * 2:接收者类型 CourseTimer 的实例也变为一个参数传递进来了，通过this 就可以调用 CourseTimer.()的相关方法；
     *
     * @param action [@kotlin.ExtensionFunctionType] Function1<CourseTimer, Unit>
     * @param timer CourseTimer
     */
    fun doCount(action:CourseTimer.()->Unit,timer:CourseTimer){
        timer.action()
    }

    fun doCount2(action:()->Unit){

    }
}

fun main() {
    val block = TestBlock()

    block.doCount({
                  this.down()
    },CourseTimer())

    block.doCount2 {

    }
}

class CourseTimer {

    fun down(){

    }

    fun count(){

    }
}