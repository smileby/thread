package com.baiyun.juc.atomic.adder;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author: BaiYun
 * @Date： 2021/12/7 11:35
 * @Description： 多线程计数器
 *
 *  LongAdder原理
 *  https://blog.csdn.net/jiangtianjiao/article/details/103844801/
 * LongAdder则是内部维护一个Cells数组，每个Cell里面有一个初始值为0的long型变量，在同等并发量的情况下，争夺单个变量的线程会减少，
 * 这是变相的减少了争夺共享资源的并发量，另外多个线程在争夺同一个原子变量时候，如果失败并不是自旋CAS重试，
 * 而是尝试获取其他原子变量的锁，最后当获取当前值时候是把所有变量的值累加后再加上base的值返回的。
 */
public class LongAdderDemo {

    private static final LongAdder longAdder = new LongAdder();
    public static void main(String[] args) {
        longAdder.add(123L);
        System.out.println("初始化值： " + longAdder.longValue());
        // 自增自减
        longAdder.increment();
        System.out.println("increment : " + longAdder.longValue());
        longAdder.decrement();
        System.out.println("decrement : " + longAdder.longValue());

        // 求和
        longAdder.add(10L);
        long sum = longAdder.sum();
        System.out.println("sum: " + sum);

        longAdder.add(10L);
        long sumThenReset = longAdder.sumThenReset();
        System.out.println("sumThenReset 求和结果： " + sumThenReset);
        System.out.println("sumThenReset 重置结果： " + longAdder.longValue());

        // 内容重置
        longAdder.add(10L);
        longAdder.reset();
        System.out.println("reset : " + longAdder.longValue());



        // 类型转换
        longAdder.intValue();
        longAdder.floatValue();
        longAdder.doubleValue();
        longAdder.longValue();


    }
}
