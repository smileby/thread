package com.baiyun.volatile_;

import java.util.concurrent.TimeUnit;

/**
 * @author baiyun
 * @date 2021-12-13-21:53
 *  内存可见性的问题
 *  volatile并不能保证原子性，但是它可以保证 有序性（happens before） 和 内存可见性
 */
public class VolatileDemo1 {

    /*
     * 有volatile和没有是两种完全不一样的结局，当没有volatile关键字修饰的时候，线程将会阻塞
     *
     */
//    private volatile static int init_value = 0;
    private static int init_value = 0;

    private static final int MAX_LIMIT = 5;

    public static void main(String[] args) {

        new Thread(() ->{
            int localValue = init_value;
            while (localValue < MAX_LIMIT){
                if(localValue != init_value){
                    System.out.printf("the value update to [%d]\n", init_value);
                    localValue = init_value;
                }
            }

        }, "Reader").start();

        new Thread(() -> {
            int localValue = init_value;
            while (init_value < MAX_LIMIT){
                System.out.printf("update the value to [%d]\n", ++localValue);
                init_value = localValue;
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Updater").start();

    }

}
