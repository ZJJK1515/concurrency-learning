package com.wy.concurrencylearning.singleton;

import com.wy.concurrencylearning.annotation.ThreadSafe;

@ThreadSafe
public class SingletonExample2 {

    private SingletonExample2() {}

    private SingletonExample2 instance = new SingletonExample2();

    public SingletonExample2 getInstance() {
        return instance;
    }
}
