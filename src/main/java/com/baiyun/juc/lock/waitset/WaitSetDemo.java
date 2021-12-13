package com.baiyun.juc.lock.waitset;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author baiyun
 * @date 2021-12-13-21:24
 */
public class WaitSetDemo {

    private static final Object LOCK = new Object();


    public static void main(String[] args) throws InterruptedException {

        /*
            1、wait方法会导致当前线程block住，并把他放到这个对象的waitset里面去，并且释放synchronize的执行权
            2、任何对象都有一个waitset， 用来存放调用了这个对象wait方法之后而block住的线程
            3、线程被notify之后，并不一定会得到立即的执行，而是进入runnable状态
            4、线程被唤醒之后的执行，并不一定是FIFO的
            5、
         */
        IntStream.rangeClosed(1, 10).forEach(i  -> new Thread(String.valueOf(i)){
            @Override
            public void run() {
                synchronized (LOCK){
                    System.out.println(Thread.currentThread().getName() + " - come waitset");
                    try {
                        // wait执行完成后，继续执行需要重新获取锁，而底层记录了代码的执行记录，并不会执行wait方法之前的内容
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " - leave waitset");
                }
            }
        }.start());
        // 由于start方法是立即返回的， sleep几秒尽量使得创建的线程进入running状态，不然日志很乱
        TimeUnit.SECONDS.sleep(3);

//        synchronized (LOCK){
//            LOCK.notifyAll();
//        }

        IntStream.rangeClosed(1, 10).forEach(i ->{
            synchronized (LOCK){
                LOCK.notify();
            }
        });
    }
}
