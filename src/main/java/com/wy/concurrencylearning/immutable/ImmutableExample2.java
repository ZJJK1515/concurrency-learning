package com.wy.concurrencylearning.immutable;


import com.google.common.collect.Maps;
import com.wy.concurrencylearning.annotation.ThreadSafe;

import java.util.Collections;
import java.util.Map;

@ThreadSafe
public class ImmutableExample2 {

    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1,1);
        //把map变成不可修改的 map
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
//        会抛出异常
//        map.put(1,1);
        System.out.println(map);
    }

}
