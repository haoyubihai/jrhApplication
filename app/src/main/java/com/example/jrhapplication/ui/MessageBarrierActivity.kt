package com.example.jrhapplication.ui

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import androidx.annotation.RequiresApi
import com.example.jrhapplication.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_message_barrier.*

/**
 * 测试目的：
 *
 * Handler 处理消息一共有3中类型的消息
 * 1. 同步消息: 既我们平常经常发送的消息
 * 2. 异步消息: 通过setAsynchronous 设置为true 的消息 或者 通过
 *          Handler(boolean async) 构造方法传入true [注意该方式没有开放给开发者使用，需要用到反射，该方式会对messageQueue中的消息遍历调研setAsynchronous]
 * 3. 同步屏障消息: 需要通过反射调用[MessageQueue.postSyncBarrier]方法，注意同步屏障消息，会阻断正常的同步消息，
 *                 处理完异步消息记得要反射调用[MessageQueue.removeSyncBarrier]及时移除同步消息屏障,否则同步消息会一直得不到解决，
 *                 一直阻塞在nativePollOnce,可能出现界面阻塞或者意想不到的情况。
 *
 *                 系统的一些高优先级操作会用到同步消息屏障，比如View的绘制操作，最终会调用到ViewRootImpl的scheduleTraversals方法，
 *                 为了保证view的优先绘制操作，在绘制之前往MessageQueue中插入同步消息屏障，在绘制结束后移除消息屏障，
 *
 *
 *
 *
 *  MessageQueue next()流程
 *  MessageQueue 是一个链表的数据结构，进入next() 判断MessageQueue的头部是不是一个同步屏障消息，同步屏障消息
 *  就是给同步消息增加一层屏障，让同步消息不被处理，只会处理异步消息。
 *
 *  如果遇到同步屏障消息，就会调整MessageQueue中的同步消息，只获取里面的异步消息来处理。如果没有异步消息就会进入nativePollOnce阻塞。
 *
 *  如果looper能正常获取消息，不管是异步消息或者同步消息，处理流程是一样的，先判断是否延时，是的话，就计时同步Linux底层
 *  nativePollOnce阻塞一定的时间，如果不是delay消息，就直接返回这个msg,并交给handler处理。
 *
 *  MessageQueue的next()方法其实就是不断的从MessageQueue中取消息，有消息就处理，没消息就调用nativePollOnce进行阻塞，这里就涉及Linux的
 *  epoll机制，IO多路复用机制。
 *
 *
 *
 * View在绘制之前添加同步消息屏障，插入FrameDisplayEventReceiver 监听sync 信号的回调，FrameDisplayEventReceiver是一个runnable,里面有一个
 * run方法在run方法内部调用了doFrame()方法，这就是view真正开始绘制的地方，以后依次调用doTraversal() performTraversals() --onMeasure() onLayout() onDraw()方法
 *
 * FrameDisplayEventReceiver中的onSync()方法接受信号回调，并发送异步消息，通知UI绘制。
 *
 *
 * @property message Int
 * @property messageCall Callback
 * @property token Int?
 */
@RequiresApi(Build.VERSION_CODES.M)
class MessageBarrierActivity : BaseActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_barrier)

        sendNormalMessage()
        btnBarrierSend.setOnClickListener {
            openBarrier()
        }
        btnSendAsync.setOnClickListener {
            sendAsyncMessage()
        }
        removeBarrier.setOnClickListener{
            removeMessageBarrier()
        }
    }

    var message = 0
    private fun sendNormalMessage() {
        Handler(messageCall).sendMessageDelayed(Message.obtain().apply {
            what = ++message
        }, 1000)
    }


    private val messageCall = Handler.Callback {
        tvContent.text = "${it.obj}&&${it.what}"
        if (token!=null){
            sendAsyncMessage("200",2000)
            if (it.obj=="async-200"){
                removeMessageBarrier()
                token =null
            }

        }
        sendNormalMessage()
        false
    }

    private fun sendAsyncMessage(msg:String="",delay:Long=100) {
        Handler(messageCall).sendMessageDelayed(Message.obtain().apply {
            what = ++message
            obj = "async-${msg}"
            isAsynchronous = true
        }, delay)

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun openBarrier() {
        openMessageBarrier()
        sendAsyncMessage()

    }

    var token :Int?=null
    @RequiresApi(Build.VERSION_CODES.M)
    private fun openMessageBarrier() {
        val msgQueue = mainLooper.queue
        val msgClass = MessageQueue::class.java
        val postBarrier = msgClass.getDeclaredMethod("postSyncBarrier")
        postBarrier.isAccessible = true
        token = postBarrier.invoke(msgQueue) as Int
        println("token=$token")
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun removeMessageBarrier() {
        println("removeMessageBarrier token=$token")
        val msgQueue = mainLooper.queue
        val msgClass = MessageQueue::class.java
        val removeBarrier = msgClass.getDeclaredMethod("removeSyncBarrier",Int::class.java)
        removeBarrier.isAccessible = true
        removeBarrier.invoke(msgQueue,token)
    }
}