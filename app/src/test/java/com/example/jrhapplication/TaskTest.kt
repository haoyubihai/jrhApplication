package com.example.jrhapplication

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.sync.Mutex
import java.lang.Thread.sleep
import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock


val task1: () -> String = {
    sleep(2000)
    "Hello".also { println("task1 finished: $it") }
}

val task2: () -> String = {
    sleep(3000)
    "World".also { println("task2 finished: $it") }
}

val task3: (String, String) -> String = { p1, p2 ->
    sleep(2000)
    "$p1 $p2".also { println("task3 finished: $it") }
}

fun main() {
//    TaskTest().testJoin()
//    TaskTest().testReentrantLock()
//    TaskTest().taskCountDownLatch()
//    TaskTest().taskCyclicBarrier()

//    TaskTest().testCAS()
//    TaskTest().testFuture()
//    TaskTest().testCompletableFuture()
//    TaskTest().flow()
    TaskTest().testKotlinMutex()
//    TaskTest().testCoroutine()
}

class TaskTest {

    lateinit var st1: String
    lateinit var st2: String

    /**
     * 在很多情况下，主线程创建并启动子线程，如果子线程中要进行大量的耗时运算，主线程将可能早于子线程结束。
     * 如果主线程需要知道子线程的执行结果时，就需要等待子线程执行结束了。主线程可以sleep(xx),但这样的xx时间不好确定，
     * 因为子线程的执行时间不确定，join()方法比较合适这个场景。
     */
    fun testJoin() {

        val t1 = Thread {
            st1 = task1()
        }
        val t2 = Thread {
            st2 = task2()
        }

        t1.start()
        t2.start()

        t1.join()
        t2.join()

        task3(st1, st2)

    }


    fun testReentrantLock() {

        val lock = ReentrantLock()

        Thread {
            lock.lock()
            st1 = task1()
            lock.unlock()
        }.start()

        st2 = task2()

        lock.lock()
        task3(st1, st2)
        lock.unlock()


    }

    fun taskCountDownLatch() {
        val countDownLatch = CountDownLatch(2)
        val t1 = Thread {
            st1 = task1()
            countDownLatch.countDown()
        }
        val t2 = Thread {
            st2 = task2()
            countDownLatch.countDown()
        }
        t1.start()
        t2.start()
        countDownLatch.await()
        task3(st1, st2)

    }


    /**
     * CyclicBarrier 是 JUC 提供的另一种共享锁机制，它可以让一组线程到达一个同步点后再一起继续运行，
     * 其中任意一个线程未达到同步点，其他已到达的线程均会被阻塞。与 CountDownLatch 的区别在于 CountDownLatch 是一次性的，
     * 而 CyclicBarrier 可以被重置后重复使用，这也正是 Cyclic 的命名由来，可以循环使用
     *
     */
    fun taskCyclicBarrier() {
        val cyclicbarrier = CyclicBarrier(3)
        val t1 = Thread {
            st1 = task1()
            cyclicbarrier.await()
        }
        val t2 = Thread {
            st2 = task2()
            cyclicbarrier.await()
        }
        t1.start()
        t2.start()
        cyclicbarrier.await()
        task3(st1, st2)

    }

    fun testCAS(){

        val cas = AtomicInteger(2)

        val t1 = Thread {
            st1 = task1()
          cas.decrementAndGet()
        }
        val t2 = Thread {
            st2 = task2()
          cas.decrementAndGet()
        }
        t1.start()
        t2.start()

        while (cas.get()!=0){}

        task3(st1, st2)

    }

    fun  testFuture() {

        val f1 = FutureTask(Callable(task1))
        val f2 = FutureTask(Callable(task2))
        Executors.newCachedThreadPool().execute ( f1 )
        Executors.newCachedThreadPool().execute ( f2 )

        task3(f1.get(), f2.get())
    }

    fun testCompletableFuture(){
        CompletableFuture.supplyAsync(task1)
            .thenCombine(CompletableFuture.supplyAsync(task2)){ p1,p2 ->
                task3(p1, p2)

            }.join()
    }

    fun testCoroutine(){
        runBlocking {

            val s1 = async(Dispatchers.IO) { task1() }
            val s2 = async(Dispatchers.IO) { task2() }
            task3(s1.await(), s2.await())
        }
    }

    fun flow(){

        val s1 = kotlinx.coroutines.flow.flow<String> { emit(task1())  }
        val s2 = kotlinx.coroutines.flow.flow<String> { emit(task2())  }

        runBlocking {
            s1.zip(s2){ t1,t2 ->
                task3(t1,t2)

            }.flowOn(Dispatchers.IO).collect {  }
        }


    }
    fun testKotlinMutex(){

        val mutex = Mutex()

        runBlocking {

            val t1 = launch(Dispatchers.IO) {
            mutex.lock()
               st1 =  task1()
                mutex.unlock()
            }

            val t2 = launch(Dispatchers.IO) {
               st2= task2()
            }

            joinAll(t1,t2)
            task3(st1, st2)
        }

    }



}

