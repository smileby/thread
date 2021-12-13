package com.baiyun.basic.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author: BaiYun
 * @Date： 2021/12/13 14:28
 * @Description：
 *   ExceptionThreadDemo1中run()方法抛出异常，在main方法中无法捕获的问题进行处理
 *
 *  需要修改 Executor 产生线程的方式，Java5 提供了一个新的接口 Thread.UncaughtExceptionHandler ，
 *  它允许在每个 Thread 上都附着一个异常处理器。
 *
 *  在程序中添加了额外的追踪机制，用来验证工厂创建的线程会传递给UncaughtExceptionHandler。
 *  可以看到，未捕获的异常是通过 uncaughtException 来捕获的。
 */
public class ExceptionThreadDemo2 implements Runnable{

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by " + t);
        System.out.println("eh = " + t.getUncaughtExceptionHandler());
        // 手动抛出异常
        throw new RuntimeException();
    }

    // 实现Thread.UncaughtExceptionHandler 接口，创建异常处理器
    static class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

        /**
         * Thread.UncaughtExceptionHandler.uncaughtException() 会在线程因未捕获临近死亡时被调用。
         */
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("caught " + e);
        }
    }

    static class HandlerThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            System.out.println(this + " creating new Thread");
            Thread t = new Thread(r);
            System.out.println("created " + t);
            t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            System.out.println("ex = " + t.getUncaughtExceptionHandler());
            return t;
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool(new HandlerThreadFactory());
        service.execute(new ExceptionThreadDemo2());
    }
}
