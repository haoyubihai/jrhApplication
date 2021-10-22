
## Bitmap 
1. Bitmap 实际占用的内存大小= 图片在设备上展示的实际宽*高*图片质量
> 与具体的设备有关，与设备的屏幕像素密度有关，和图片具体所在的是那个目录没有太大的关系，注意实际占用的内存并不是图片本身的宽高算的。

## View
1. activity 
    * onCreate() 通过setContentView(View) 方法解析布局，inflate之后，会走onFinishInflate()方法，这时只是将view add到DecorView中，还没有关联到window中
    * onResume() 中通过makeVisiable() PhoneWindow.addView() 将view 绑定到window上进行显示。
    * onAttachedToWindow() 这里回调之后，证明界面完全显示了
 > View真正开始绘制的时机是执行完onResume()之后，onResume()之后才开始执行窗口的创建，以及DecorView的绘制操作，所以在这之前你是无法获取view
   的宽高的。
2. window 
> window 机制就是为了管理屏幕上view的显示以及触摸事件传递的问题的。
> 每一个view树都是一个window ， view树是window的展现形式，window是view树的载体，比如：activity，dialog，popwindow,toast 都对应各自的view树，对应各自的window 
  并且都是有windowManager进行管理。
> window 是一个抽象的概念，本身不存在，我们看到屏幕上显示的是view，window的展现形式，window只是view的载体。
> window 要控制view如何显示
> 代码层面 window是一个抽象类，PhoneWindow是具体实现
> ViewRootImpl是window和view之间的桥梁，可以处理两边的对象，并将二者关联起来。

3.https

4. 挂起函数
 suspend 关键字
 被suspend的标记的函数即为挂起函数。
 挂起函数是如何被挂起的？
 suspend 函数在编译时，会传入一个Continuation 的实例，Continuation 是一个接口，里面有CoroutineContext 保存上下文
 还有一个resumeWith 方法，suspend 函数的恢复执行,就是通过调用resumeWith方法执行。同时会自动生成一些lablel 标签，系统
 处理为状态机的形式进行执行。挂起 通过return，遇到suspend函数挂起点 直接 return
 
 ```
/**
 * Interface representing a continuation after a suspension point that returns a value of type `T`.
 */
@SinceKotlin("1.3")
public interface Continuation<in T> {
    /**
     * The context of the coroutine that corresponds to this continuation.
     */
    public val context: CoroutineContext

    /**
     * Resumes the execution of the corresponding coroutine passing a successful or failed [result] as the
     * return value of the last suspension point.
     */
    public fun resumeWith(result: Result<T>)
}

```
 