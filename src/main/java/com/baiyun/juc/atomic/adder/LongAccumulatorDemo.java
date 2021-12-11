package com.baiyun.juc.atomic.adder;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;

/**
 * @author: BaiYun
 * @Date： 2021/12/7 11:35
 * @Description：
 */
public class LongAccumulatorDemo {

    // u:  操作过程中传进来的值， v： 当前值（该方法初始为10）
    private static final LongBinaryOperator operator = (u, v) -> u + v;
    private static final LongAccumulator longAccumulator = new LongAccumulator(operator, 10L);
    public static void main(String[] args) {

        long la = longAccumulator.get(); // 初始值10
        System.out.println("getThenReset for get: " + la);

        //  accumulate = 120 + 10
        longAccumulator.accumulate(120);

        // 获取
        long l = longAccumulator.get();
        System.out.println("getThenReset for get: " + l);

        // 获取后重置
        long thenReset = longAccumulator.getThenReset();
        System.out.println("getThenReset for get: " + thenReset);
        System.out.println("getThenReset for reset: " + longAccumulator.longValue());

        // 重置
        longAccumulator.reset();
        System.out.println("reset : " + longAccumulator.longValue());

        // 类型转换
        longAccumulator.intValue();
        longAccumulator.floatValue();
        longAccumulator.doubleValue();
        longAccumulator.longValue();

    }
}
