package com.wy.concurrencylearning.singleton;

import com.wy.concurrencylearning.annotation.ThreadSafe;

@ThreadSafe
public class SingletonExample3 {

    private SingletonExample3() {}

    private static SingletonExample3 instance = null;

    public static synchronized SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }

}
