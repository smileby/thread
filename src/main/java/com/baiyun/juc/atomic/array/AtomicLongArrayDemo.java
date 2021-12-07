package com.baiyun.juc.atomic.array;

import java.util.concurrent.atomic.AtomicLongArray;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;

/**
 * @author: BaiYun
 * @Date： 2021/12/7 11:32
 * @Description： AtomicLongArray 可用方法与 AtomicIntegerArray 一致
 */
public class AtomicLongArrayDemo {

    private static AtomicLongArray atomicLongArray = new AtomicLongArray(10);


    public static void main(String[] args) {
        System.out.println("给指定坐标，设置一个值并且获取该值");
        atomicLongArray.lazySet(0, 10);
        System.out.println(atomicLongArray.get(0));

        System.out.println("给指定坐标，设置一个值并且获取该值");
        atomicLongArray.set(0, 20);
        System.out.println(atomicLongArray.get(0));
        System.out.println("获取数组长度");
        System.out.println(atomicLongArray.length());
        System.out.println("指定坐标，符合预期值，则更新为新值");
        System.out.println(atomicLongArray.compareAndSet(0, 20, 30));
        System.out.println(atomicLongArray.get(0));

        System.out.println("指定坐标，先自增一，然后获取该值");
        System.out.println(atomicLongArray.incrementAndGet(0));
        System.out.println("指定坐标，先获取该值，然后将该值自增一");
        System.out.println(atomicLongArray.getAndIncrement(0));
        System.out.println(atomicLongArray.get(0));
        System.out.println("指定坐标，先自减一，然后获取该值");
        System.out.println(atomicLongArray.decrementAndGet(0));
        System.out.println("指定坐标，先获取该值，然后将该值自减一");
        System.out.println(atomicLongArray.getAndDecrement(0));
        System.out.println(atomicLongArray.get(0));

        System.out.println("指定坐标，先增加指定值，然后获取该值");
        System.out.println(atomicLongArray.addAndGet(0, 5));

        System.out.println("指定坐标，先获取该值，增加指定值");
        System.out.println(atomicLongArray.getAndAdd(0, 5));
        System.out.println(atomicLongArray.get(0));


        System.out.println("指定坐标，原子的更新该值，然后获取");
        int x = 10;
        // u: 当前坐标对应的值， v： 变量值
        LongBinaryOperator accumulatorFunction = (u, v) -> u / v;
        System.out.println(atomicLongArray.accumulateAndGet(0, x, accumulatorFunction));
        System.out.println("指定坐标，先获取该值，然后原子的更新该值");
        LongBinaryOperator accumulatorFunction2 = (u, v) -> u + v;
        System.out.println(atomicLongArray.getAndAccumulate(0, x, accumulatorFunction2));
        System.out.println(atomicLongArray.get(0));
        System.out.println("指定坐标，先获取该值，然后原子的修改该值");
        LongUnaryOperator updateFunction = u -> u * 2;
        System.out.println(atomicLongArray.getAndUpdate(0, updateFunction));
        System.out.println(atomicLongArray.get(0));
        System.out.println("指定坐标，先原子的修改该值，然后获取该值");
        System.out.println(atomicLongArray.updateAndGet(0, updateFunction));
    }
}
