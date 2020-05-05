package com.wy.concurrencylearning.singleton;

import com.wy.concurrencylearning.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@NotThreadSafe
public class SingletonExample1 {

    private SingletonExample1() {}

    private static SingletonExample1 instance = null;

    public static SingletonExample1 getInstance() {
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }

}
