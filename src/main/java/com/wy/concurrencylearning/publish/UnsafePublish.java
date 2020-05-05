package com.wy.concurrencylearning.publish;

import com.wy.concurrencylearning.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 多个线程均可访问并修改一个资源时，容易引发线程安全问题
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"a", "b", "c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
    }
}
