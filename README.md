# BitmapCanary
Detect Android bitmap size when app runing,检查安卓运行时使用的bitmap大小，检查出过大的位图并展示

    介绍：
    Android工程中会使用大量的图片，但是如果图片放置的drawable目录位置不正确，可能会导致图片占用的内存过大，可能导致cpu使用更多时间分配更多内存，可能导致内存溢出
    现在可以使用BitmapCanary来方便开发者检查工程中过大的图片，可视化展示，一句代码即可接入该功能
    使用方法:
    下载并依赖androidbitmapcanary
    ActivityDrawableWatcher.watchDrawable(this);//这样就ok啦
    
    
    
![image](https://github.com/smallnew/BitmapCanary/raw/master/detect_demo.png)
