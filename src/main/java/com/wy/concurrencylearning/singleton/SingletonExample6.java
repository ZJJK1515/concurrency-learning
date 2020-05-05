package com.wy.concurrencylearning.singleton;

import com.wy.concurrencylearning.annotation.ThreadSafe;

@ThreadSafe
public class SingletonExample6 {

    private SingletonExample6() {

    }


     private static SingletonExample6 instance = null;
    /**
     * 静态域如果写在变量声明前，会将instance重新赋值为null
     */
    static {
        instance = new SingletonExample6();
    }

//    private static SingletonExample6 instance = null;

    public static SingletonExample6 getInstance() {
        return instance;
    }


    public static void main(String[] args) {
        System.out.println(getInstance());
        System.out.println(getInstance());
    }


}
