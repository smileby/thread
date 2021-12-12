package com.baiyun.basic.create;

/**
 * @author baiyun
 * @date 2021-12-11-21:08
 *
 * 使用Thread的方式创建一个线程, 只有start之后，才会是一个线程，否则直接调用run方法，只是执行了该方法.
 *
 * start方法是立即返回的，且一个线程只能调用start一次，如果再次调用会抛出异常
 */
public class ThreadDemo {

    static class TestThread extends Thread{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        testThread.start();

        // 给线程指定一个名字
        Thread thread = new Thread("customer") {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "start");
            }
        };
        thread.start();

        new Thread("customer2"){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "start");
            }
        }.start();

    }


}
