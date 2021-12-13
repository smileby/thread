package com.baiyun.basic.threadmethod;

import java.util.concurrent.TimeUnit;

/**
 * @author: BaiYun
 * @Date： 2021/12/13 11:37
 * @Description： 守护线程
 */
public class DaemonDemo {
    static class SimpleDaemons implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                    System.out.println(Thread.currentThread() + " " + this);
                } catch (InterruptedException e) {
                    System.out.println("sleep() interrupted");
                }
            }
        }
    }

    /**
     *
     * 创建10个线程，并把每个线程设置为后台线程，然后开始运行，for循环会进行十次，然后输出信息，
     * 随后主线程睡眠一段时间后停止运行。在每次run 循环中，都会打印当前线程的信息，主线程运行完毕，
     * 程序就执行完毕了。因为 daemon 是后台线程，无法影响主线程的执行。
     *
     * 当你把 daemon.setDaemon(true) 去掉时，while(true) 会进行无限循环，
     * 那么主线程一直在执行最重要的任务，所以会一直循环下去无法停止。
     */
    public static void main(String[] args) throws InterruptedException {
        for(int i = 0;i < 10;i++){
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("All Daemons started");
        TimeUnit.SECONDS.sleep(10);
    }
}
