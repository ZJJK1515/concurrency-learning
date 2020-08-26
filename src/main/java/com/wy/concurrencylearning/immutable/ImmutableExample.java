package com.wy.concurrencylearning.immutable;



import com.google.common.collect.Maps;
import com.wy.concurrencylearning.annotation.NotThreadSafe;

import java.util.Map;

@NotThreadSafe
public class ImmutableExample {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
    }


    public static void main(String[] args) {
//        基本类型不允许修改
//        a = 2;
//        b = "a";

//        引用类型不允许指向新对象，但允许 put 新值
//        map = Maps.newHashMap();
        map.put(4,4);
    }

    private void test(final int a) {
//        a = 1;
    }
}
