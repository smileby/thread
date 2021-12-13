package com.baiyun.basic.threadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author: BaiYun
 * @Date： 2021/12/13 13:57
 * @Description： 实现一个守护线程工厂
 */
public class DaemonThreadFactory {

    static class DaemonThreadFactoryDemo implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    }
    static class DaemonFromFactory implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println(Thread.currentThread() + " " + this);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool(new DaemonThreadFactoryDemo());
        for(int i = 0;i < 10;i++){
            service.execute(new DaemonFromFactory());
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(5000);
    }
}