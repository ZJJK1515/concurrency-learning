package com.wy.concurrencylearning.singleton;

import com.wy.concurrencylearning.annotation.NotThreadSafe;

/**
 * 双重同步锁，但其实并不能保证线程安全
 */
@NotThreadSafe
public class SingletonExample4 {
    private SingletonExample4() {

    }

    /**
     * 1.分配对象内存
     * 2.初始化对象
     * 3.设置 instance 指向分配的内存
     * 由于 2 3 两步可能会JVM、CPU优化发生指令重排序，可能会引发线程安全问题
     */
    private static SingletonExample4 instance = null;

    public static SingletonExample4 getInstance() {
        if (instance == null) {
            synchronized (SingletonExample4.class) {
                if (instance == null) {
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }


}
