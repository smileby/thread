package com.baiyun.basic.create;

/**
 * @author baiyun
 * @date 2021-12-11-21:13
 */
public class RunnableDemo {

    static class TestRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Thread thread = new Thread(new TestRunnable());
        thread.start();
    }
}
