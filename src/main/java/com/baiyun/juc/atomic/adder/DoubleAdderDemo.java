package com.baiyun.juc.atomic.adder;

import java.util.concurrent.atomic.DoubleAdder;

/**
 * @author: BaiYun
 * @Date： 2021/12/7 11:35
 * @Description： 多线程计数器
 */
public class DoubleAdderDemo {

    private static final DoubleAdder doubleAdder = new DoubleAdder();
    public static void main(String[] args) {

        doubleAdder.add(123L);
        System.out.println("初始化值： " + doubleAdder.doubleValue());


        // 求和
        doubleAdder.add(10L);
        double sum = doubleAdder.sum();
        System.out.println("sum: " + sum);

        doubleAdder.add(10L);
        double sumThenReset = doubleAdder.sumThenReset();
        System.out.println("sumThenReset 求和结果： " + sumThenReset);
        System.out.println("sumThenReset 重置结果： " + doubleAdder.doubleValue());

        // 内容重置
        doubleAdder.add(10L);
        doubleAdder.reset();
        System.out.println("reset : " + doubleAdder.doubleValue());



        // 类型转换
        doubleAdder.intValue();
        doubleAdder.floatValue();
        doubleAdder.doubleValue();
        doubleAdder.longValue();
    }
}
