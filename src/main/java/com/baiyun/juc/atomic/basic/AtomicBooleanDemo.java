package com.baiyun.juc.atomic.basic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author: BaiYun
 * @Date： 2021/12/7 11:26
 * @Description：
 */
public class AtomicBooleanDemo {
    private static AtomicBoolean atomicBoolean = new AtomicBoolean(true);
    public static void main(String[] args) {
        System.out.println("atomicBoolean默认值");
        System.out.println(atomicBoolean.get());

        System.out.println("使用set更新值");
        atomicBoolean.set(false);
        System.out.println(atomicBoolean.get());
        // set()会立刻修改旧值，别的线程可以立刻看到更新后的值；而lazySet不会立刻(但是最终会)修改旧值，
        System.out.println("使用lazySet设置值");
        atomicBoolean.lazySet(true);
        System.out.println(atomicBoolean.get());

        System.out.println("使用compareAndSet设置值");
        boolean b = atomicBoolean.compareAndSet(true, false);
        System.out.println(atomicBoolean.get());

        System.out.println("使用weakCompareAndSet设置值");
        boolean f = atomicBoolean.weakCompareAndSet(false, true);
        System.out.println(atomicBoolean.get());

        System.out.println("获取当前值，并且设置新值");
        System.out.println(atomicBoolean.getAndSet(false));
        System.out.println(atomicBoolean.get());
    }
}
