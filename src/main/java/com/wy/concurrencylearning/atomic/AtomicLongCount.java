package com.wy.concurrencylearning.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class AtomicLongCount {

    // 请求总数
    public static final int clientTotal = 5000;

    // 同时并发执行的线程数
    public static final int threadTotal = 200;

    public static AtomicLong count = new AtomicLong(0);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.info("error: ", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{} ",count);
    }

    private static void add() {
        // 两种方式只对获取返回值有影响
        // 底层调用了 unsafe 类中的 compareAndSwapInt() 方法 (CAS)
        //   使用 do while 语句循环判断当前 count 与底层拿到的当前值是否相同
        //   如果相同，则直接进行加操作；
        //   如果不同，则将当前底层值赋给 count，继续执行循环操作
        count.incrementAndGet();
//        count.getAndIncrement();
    }
}
