
## Bitmap 
1. Bitmap 实际占用的内存大小= 图片在设备上展示的实际宽*高*图片质量
> 与具体的设备有关，与设备的屏幕像素密度有关，和图片具体所在的是那个目录没有太大的关系，注意实际占用的内存并不是图片本身的宽高算的。

## View
1. activity 
    * onCreate() 通过setContentView(View) 方法解析布局，inflate之后，会走onFinishInflate()方法，这时只是将view add到DecorView中，还没有关联到window中
    * onResume() 中通过makeVisiable() PhoneWindow.addView() 将view 绑定到window上进行显示。
    * onAttachedToWindow() 这里回调之后，证明界面完全显示了