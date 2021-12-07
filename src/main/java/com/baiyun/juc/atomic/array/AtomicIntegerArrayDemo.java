package com.baiyun.juc.atomic.array;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

/**
 * @author: BaiYun
 * @Date： 2021/12/7 11:32
 * @Description： AtomicIntegerArray 可用方法与 AtomicLongArray 一致
 */
public class AtomicIntegerArrayDemo {

    private static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);

    public static void main(String[] args) {
        System.out.println("给指定坐标，设置一个值并且获取该值");
        atomicIntegerArray.lazySet(0, 10);
        System.out.println(atomicIntegerArray.get(0));

        System.out.println("给指定坐标，设置一个值并且获取该值");
        atomicIntegerArray.set(0, 20);
        System.out.println(atomicIntegerArray.get(0));
        System.out.println("获取数组长度");
        System.out.println(atomicIntegerArray.length());
        System.out.println("指定坐标，符合预期值，则更新为新值");
        System.out.println(atomicIntegerArray.compareAndSet(0, 20, 30));
        System.out.println(atomicIntegerArray.get(0));

        System.out.println("指定坐标，先自增一，然后获取该值");
        System.out.println(atomicIntegerArray.incrementAndGet(0));
        System.out.println("指定坐标，先获取该值，然后将该值自增一");
        System.out.println(atomicIntegerArray.getAndIncrement(0));
        System.out.println(atomicIntegerArray.get(0));
        System.out.println("指定坐标，先自减一，然后获取该值");
        System.out.println(atomicIntegerArray.decrementAndGet(0));
        System.out.println("指定坐标，先获取该值，然后将该值自减一");
        System.out.println(atomicIntegerArray.getAndDecrement(0));
        System.out.println(atomicIntegerArray.get(0));

        System.out.println("指定坐标，先增加指定值，然后获取该值");
        System.out.println(atomicIntegerArray.addAndGet(0, 5));

        System.out.println("指定坐标，先获取该值，增加指定值");
        System.out.println(atomicIntegerArray.getAndAdd(0, 5));
        System.out.println(atomicIntegerArray.get(0));


        System.out.println("指定坐标，原子的更新该值，然后获取");
        int x = 10;
        // u: 当前坐标对应的值， v： 变量值
        IntBinaryOperator accumulatorFunction = (u, v) -> u / v;
        System.out.println(atomicIntegerArray.accumulateAndGet(0, x, accumulatorFunction));
        System.out.println("指定坐标，先获取该值，然后原子的更新该值");
        IntBinaryOperator accumulatorFunction2 = (u, v) -> u + v;
        System.out.println(atomicIntegerArray.getAndAccumulate(0, x, accumulatorFunction2));
        System.out.println(atomicIntegerArray.get(0));
        System.out.println("指定坐标，先获取该值，然后原子的修改该值");
        IntUnaryOperator updateFunction = value -> value * 2;
        System.out.println(atomicIntegerArray.getAndUpdate(0, updateFunction));
        System.out.println(atomicIntegerArray.get(0));
        System.out.println("指定坐标，先原子的修改该值，然后获取该值");
        System.out.println(atomicIntegerArray.updateAndGet(0, updateFunction));

    }
}
