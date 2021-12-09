package com.baiyun.juc.tools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: BaiYun
 * @Date： 2021/12/9 14:09
 * @Description：
 *
 * 概念：
 *      CountDownLatch 是一个同步工具类，使用计数器的方式来进行实现，协调多个线程之间的同步，能够使一个线程在其他线程完成工作后在继续执行，
 *      计数器的数量是初始线程的数量，当每个线程在完成工作后，将计数器减一，当计数器减为0时，表示这些线程都执行完了任务，然后在countDownLatch
 *      上等待的线程被恢复，执行接下来的任务
 *
 * 使用场景：
 *      1、 在某个服务启动时，需要等待其它一些组件加载完成后，再进行接下来的操作
 *      2、 实现某一时刻多个线程的并行性（注意: 不是并发性），强调的是多个线程在某一时刻同时执行，类似于赛跑
 *
 * 不足：
 *      CountDownLatch是一次性的，计数器的值只能初始化一次，之后没有其它机制再对其进行值的设置，当它被使用完毕后，也就不能再被使用
 *
 * 方法说明：
 *      countDown()：
 *          递减计数，如果计数器为0，则释放所有等待线程，如果不为0，则将计数器减一
 *      await(long timeout, TimeUnit unit)：
 *          计数器为0之前，或者在指定时间之前，使当前线程一直等待，除非线程被中断或者超出了指定时间，如果计数器为0，则立刻返回true，
 *          如果计数器到达0之前超出了指定的等待时间，则返回值为false，如果这个时间小于等于0，则这个方法不会等待
 *      await()：
 *          计数器为0之前，使当前线程一直等待，除非线程被中断
 *      getCount()：
 *          获取当前计数器的剩余数量
 *      toString():
 *          返回标识此锁存器及其状态的字符串。
 *
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        CountDownLatch cdl = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            Runnable runnable = () -> {
                System.out.println("子线程：" + Thread.currentThread().getName() + "开始执行任务...");
                try {
                    // 10秒以内的随机值
                    Thread.sleep((long) (Math.random() * 10000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程：" + Thread.currentThread().getName() + "任务执行完毕...");
                System.out.println(cdl.getCount());
                System.out.println(cdl.toString());
                cdl.countDown();
            };
            pool.submit(runnable);
        }

        System.out.println("主线程：" + Thread.currentThread().getName() + "等待子线程执行结束...");
        // 一直等待，除非被中断
//        cdl.await();
        // 超出指定时间，计数器不为0,则返回false不在等待
//        boolean flag = cdl.await(1000, TimeUnit.MILLISECONDS);
        // 计数器不为0 ，且在到达指定时间之前，该方法将一致等待，除非被中断，结束后，返回true
//        boolean flag = cdl.await(20000, TimeUnit.MILLISECONDS);
//        System.out.println(flag);
        System.out.println("主线程：" + Thread.currentThread().getName() + "开始执行任务...");
    }
}
