package com.wy.concurrencylearning.cas;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CASHandler {

    /**
     * 模拟内存变量
     */
    private volatile Integer nativeCount = 0;

    /**
     * CAS 方法，使用 synchronized 模拟比较、获取、修改内存值的操作
     * @param except 预期结果
     * @param update 累加值
     * @return 比较预期结果与实际结果是否一致
     */
    public boolean compareAndSet(int except, int update) {
        boolean flag ;
            synchronized (this) {
                // 将预期结果 & 当前值与累加值(update) 的和进行比较，如果一致则将此值 set 进内存
                flag = nativeGet() + update == except;
                if (flag) {
                    nativeSet(except);
                }
            }
       if (!flag) {
           log.info("--------------------比较结果为 false，重试中...-------------------");
       }
       return flag;

    }

    // 使用synchronized 模拟从内存中获取最新值
    private synchronized Integer nativeGet() {
        return nativeCount;
    }

    /**
     * 使用 synchronized 模拟更新内存中的值
     * @param updateValue 更新的值
     */
    private synchronized void nativeSet(Integer updateValue) {
        nativeCount = updateValue;
    }

    /**
     * 线程不安全的获取值的方法，模拟并发场景
     * @return
     */
    public Integer getInteger() {
        return nativeCount;
    }

    public Integer increment() {
        int tempCount;
        do {
            tempCount = getInteger();
        } while (!compareAndSet(tempCount + 1, 1));
        return tempCount;
    }


    /**
     * 测试模拟 CAS
     * @param args
     */

    public static void main(String[] args) {
        // 创建线程池
        ExecutorService executor = Executors.newScheduledThreadPool(1000);
        final CountDownLatch countDownLatch = new CountDownLatch(5000);

        // 模拟并发量 100
        Semaphore semaphore = new Semaphore(500);

        CASHandler casHandler = new CASHandler();
            for (int i = 0; i < 5000; i++) {
                executor.execute(() -> {
                    try {
                        semaphore.acquire();
                        casHandler.increment();
//                        casHandler.nativeSet(casHandler.getInteger() + 1);
                        semaphore.release();
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("over, " + casHandler.getInteger());
        log.debug("countdownlatch: {}", countDownLatch.getCount());
        executor.shutdown();

    }

}
