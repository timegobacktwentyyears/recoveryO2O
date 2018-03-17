package com.recover;

import com.sun.jna.Library;
import com.sun.jna.Native;

//继承Library，用于加载库文件
public interface Clibrary extends Library {
    /**
     *     加载.so链接库
     */
    Clibrary INSTANTCE = (Clibrary) Native.loadLibrary("agorasdk", Clibrary.class);

    //此方法为链接库中的方法
    int sayHello();
}

