package com.example.jrhapplication

class TestPrint {
    val a = "TestPrint()"
    val b = "TestPrint()"

    fun print(){
        Thread{
            synchronized(a){
                println("1----")
                Thread.sleep(3000)
            }
        }.start()

        Thread{
            synchronized(b){
                println("2----")
                Thread.sleep(3000)
            }
        }.start()
    }
}

fun main() {
//    TestPrint().print()
    println("${30/100}--")
}