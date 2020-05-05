package com.wy.concurrencylearning.publish;

import com.wy.concurrencylearning.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;


/**
 * 此种写法意味着对象还未构造完成，其他线程就可访问到该对象，容易引发未知错误
 *
 */
@Slf4j
@NotThreadSafe
public class Escape {
    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
