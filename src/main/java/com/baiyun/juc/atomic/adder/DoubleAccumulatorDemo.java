package com.baiyun.juc.atomic.adder;

import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.function.DoubleBinaryOperator;

/**
 * @author: BaiYun
 * @Date： 2021/12/7 11:35
 * @Description：
 */
public class DoubleAccumulatorDemo {

    // u:  操作过程中传进来的值， v： 当前值（该方法初始为10）
    private static final DoubleBinaryOperator operator = (u, v) -> u + v;
    private static final DoubleAccumulator doubleAccumulator = new DoubleAccumulator(operator, 10);
    public static void main(String[] args) {

        double la = doubleAccumulator.get(); // 初始值10
        System.out.println("getThenReset for get: " + la);

        //  accumulate = 120 + 10 
        doubleAccumulator.accumulate(120);

        // 获取
        double l = doubleAccumulator.get();
        System.out.println("getThenReset for get: " + l);

        // 获取后重置
        double thenReset = doubleAccumulator.getThenReset();
        System.out.println("getThenReset for get: " + thenReset);
        System.out.println("getThenReset for reset: " + doubleAccumulator.doubleValue());

        // 重置
        doubleAccumulator.reset();
        System.out.println("reset : " + doubleAccumulator.doubleValue());

        // 类型转换
        doubleAccumulator.intValue();
        doubleAccumulator.floatValue();
        doubleAccumulator.doubleValue();
        doubleAccumulator.doubleValue();
    }
}
