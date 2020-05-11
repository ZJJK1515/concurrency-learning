package com.wy.concurrencylearning.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.wy.concurrencylearning.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class ImmutableExample3 {

    private final static ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);

    private final static ImmutableSet<Integer> set = ImmutableSet.copyOf(list);

    private final static ImmutableMap<Integer, Integer> map1 = ImmutableMap.of(1,1,2,2,3,3);

    private final static ImmutableMap map2 = ImmutableMap.builder().put(1,1).put(2,2).put(3,3).build();


    public static void main(String[] args) {
        try {
            list.add(1);
        } catch (Exception e) {
            log.info("错误");
        }
        try {
            set.add(1);
        } catch (Exception e) {
            log.info("错误");
        }
        try {
            map1.put(1,1);
        } catch (Exception e) {
            log.info("错误");
        }
        try {
            map2.put(2,2);
        } catch (Exception e) {
            log.info("错误");
        }
    }


}
