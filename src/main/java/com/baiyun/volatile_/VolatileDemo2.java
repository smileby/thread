package com.baiyun.volatile_;

/**
 * @author baiyun
 * @date 2021-12-13-22:29
 *
 * 在Demo1中，所有的读线程将会阻塞，而在Demo2不会（虽然有线程不安全的问题），这是由于java的内存模型导致的。
 *    所有的读操作将不会更新工作内存至主内存，而写操作会更新主内存
 */
public class VolatileDemo2 {

    private static int MAX_LIMIT = 10;
    private static int init_value = 0;

    public static void main(String[] args) {

        new Thread(() ->{
            while (init_value < MAX_LIMIT){
                System.out.println("t1 -> " + (++init_value));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "T1").start();

        new Thread(() ->{
            while (init_value < MAX_LIMIT){
                System.out.println("t2 -> " + (++init_value));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "T2").start();

    }
}
