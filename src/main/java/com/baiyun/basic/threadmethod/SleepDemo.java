package com.baiyun.basic.threadmethod;

import java.util.concurrent.TimeUnit;

/**
 * @author: BaiYun
 * @Date： 2021/12/13 10:32
 * @Description： 线程休眠
 */
public class SleepDemo {


    /**
     * 影响任务执行行为的一种简单方式就是使线程进行休眠， 指定休眠的时间，然后调用sleep方法
     * 一般情况下使用TimeUnit这个时间类替换Thread.sleep()方法，在开发过程中使用
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(1000L); // 1s
//
        Thread.sleep(1000L, 500000);

        // 内部调用了Thread.sleep方法，也可能会抛出InterruptedException
        TimeUnit.SECONDS.sleep(5L); // 5s

        // TimeUnit还提供了便捷方法用于把时间转换成不同单位
        System.out.println(TimeUnit.SECONDS.toMillis(10));
    }
}
