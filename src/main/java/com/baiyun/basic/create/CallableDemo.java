package com.baiyun.basic.create;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author baiyun
 * @date 2021-12-11-21:16
 *
 * Callable在完成时能够返回一个值
 */
public class CallableDemo {

    static class TestCallable implements Callable{

        @Override
        public Object call() throws Exception {
            System.out.println(Thread.currentThread().getName());
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        FutureTask futureTask = new FutureTask(new TestCallable());
        Thread thread = new Thread(futureTask);
        thread.start();
    }
}
