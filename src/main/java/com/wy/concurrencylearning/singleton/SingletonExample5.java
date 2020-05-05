package com.wy.concurrencylearning.singleton;

import com.wy.concurrencylearning.annotation.ThreadSafe;

@ThreadSafe
public class SingletonExample5 {

    private SingletonExample5() {

    }

    // 使用 volatile 禁止指令重排序
    private volatile static SingletonExample5 instance = null;

    public static SingletonExample5 getInstance() {
        if (instance == null) {
            synchronized (SingletonExample5.class) {
                if (instance == null) {
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }

}
