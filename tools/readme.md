## 图片压缩工具  compressImage.py 使用

1. 在Mac下，homebrew安装webp工具：
   
   brew install webp
   
2. 修改代码里的modules = ["assets/images"]的图片所在的文件夹，路径是相对python脚本文件所在位置的相对路径 
3. 打开Android Studio的Terminal框，输入python3 compressImage.py 即可将我们的图片文件夹里大于指定kb的图片压缩为webp格式并删除被压缩的文件。
   
