package com.baiyun.juc.tools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: BaiYun
 * @Date： 2021/12/9 14:29
 * @Description： 使用CountDownLatch实现短跑运动员比赛的场景
 */
public class CountDownLatchDemo2 {
    public static void main(String[] args) throws InterruptedException {
        // 定义7个比赛运动员的小组
        ExecutorService teamPool = Executors.newFixedThreadPool(7);
        // 定义1个裁判
        CountDownLatch referee = new CountDownLatch(1);
        // 成绩记录仪
        CountDownLatch record = new CountDownLatch(7);

        for (int i = 0; i < 7; i++) {
            Runnable runnable = () -> {
                System.out.println("运动员：" + Thread.currentThread().getName() + "准备就绪");
                try {
                    referee.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                     System.out.println("运动员：" + Thread.currentThread().getName() + "起跑加速.....");
                    Thread.sleep((long) (Math.random() * 10000));
                    record.countDown();
                    System.out.println("运动员：" + Thread.currentThread().getName() + "到达终点。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            teamPool.submit(runnable);
        }

        System.out.println("裁判准备发令.......");
        System.out.println("十秒倒计时开始.......");
        Thread.sleep(10000);
        referee.countDown();
        System.out.println("比赛开始...........");
        record.await();
        System.out.println("所有运动员比赛结束，成绩汇总中....");

    }
}
