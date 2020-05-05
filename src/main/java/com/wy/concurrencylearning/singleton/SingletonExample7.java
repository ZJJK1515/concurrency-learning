package com.wy.concurrencylearning.singleton;

import com.wy.concurrencylearning.annotation.ThreadSafe;

@ThreadSafe
public class SingletonExample7 {

    private SingletonExample7() {}

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;
        private SingletonExample7 instance = null;

        Singleton() {
            instance = new SingletonExample7();
        }
        public SingletonExample7 getInstance() {
            return instance;
        }
    }

    public static void main(String[] args) {
        SingletonExample7 singletonExample7 = new SingletonExample7();
        System.out.println(getInstance());
        System.out.println(getInstance());
        System.out.println(getInstance());
    }
}
