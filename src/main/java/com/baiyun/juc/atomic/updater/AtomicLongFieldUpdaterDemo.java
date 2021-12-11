package com.baiyun.juc.atomic.updater;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.function.*;

/**
 * @author: BaiYun
 * @Date： 2021/12/7 11:34
 * @Description： TODO
 */
public class AtomicLongFieldUpdaterDemo {

    static class Person{
        String name;
        volatile long income;
    }
    
    private static final AtomicLongFieldUpdater<Person> ATOMIC_LONG_FIELD_UPDATER = 
            AtomicLongFieldUpdater.newUpdater(Person.class, "income");

    public static void main(String[] args) {
        Person person = new Person();
        // get和set方法都是抽象方法，这个是放在内部类中实现的方法。
        ATOMIC_LONG_FIELD_UPDATER.set(person, 10);
        ATOMIC_LONG_FIELD_UPDATER.lazySet(person, 11);
        long i = ATOMIC_LONG_FIELD_UPDATER.get(person);
        System.out.println("get: "  + i);

        // 比较设置方法
        boolean b = ATOMIC_LONG_FIELD_UPDATER.compareAndSet(person, 11, 12);
        System.out.println("compareAndSet: " + b);
        boolean b1 = ATOMIC_LONG_FIELD_UPDATER.weakCompareAndSet(person, 12, 11);
        System.out.println("weakCompareAndSet: " + b1);

        // 获取设置方法
        long andSet = ATOMIC_LONG_FIELD_UPDATER.getAndSet(person, 14);
        System.out.println("getAndSet:" + andSet);

        // 获取增加方法
        long getAdd = ATOMIC_LONG_FIELD_UPDATER.getAndAdd(person, 1);
        System.out.println("getAndAdd: " + getAdd);
        System.out.println("getAndAdd after: " + ATOMIC_LONG_FIELD_UPDATER.get(person));
        long addGet = ATOMIC_LONG_FIELD_UPDATER.addAndGet(person, 1);
        System.out.println("addAndGet: " + addGet);


        // 获取自增方法
        long getIncrement = ATOMIC_LONG_FIELD_UPDATER.getAndIncrement(person);
        System.out.println(" getAndIncrement：" + getIncrement);
        System.out.println(" getAndIncrement after：" + ATOMIC_LONG_FIELD_UPDATER.get(person));
        long incrementGet = ATOMIC_LONG_FIELD_UPDATER.incrementAndGet(person);
        System.out.println(" incrementAndGet：" + incrementGet);

        // 获取自减
        long getDecrement = ATOMIC_LONG_FIELD_UPDATER.getAndDecrement(person);
        System.out.println(" getAndDecrement：" + getDecrement);
        System.out.println(" getAndDecrement after：" + ATOMIC_LONG_FIELD_UPDATER.get(person));
        long decrementGet = ATOMIC_LONG_FIELD_UPDATER.decrementAndGet(person);
        System.out.println(" decrementAndGet：" + decrementGet);

        // 获取更新方法
        LongUnaryOperator luo = (u) -> u * 2;
        long getUpdate = ATOMIC_LONG_FIELD_UPDATER.getAndUpdate(person, luo);
        System.out.println(" getAndUpdate：" + getUpdate);
        System.out.println(" getAndUpdate after：" + ATOMIC_LONG_FIELD_UPDATER.get(person));
        long updateGet = ATOMIC_LONG_FIELD_UPDATER.updateAndGet(person, luo);
        System.out.println(" updateAndGet：" + updateGet);

        System.out.println();

        // 获取累加方法  score = 64 + 2
        LongBinaryOperator lbo = (u, x) -> u + x;
        long getAccumulate = ATOMIC_LONG_FIELD_UPDATER.getAndAccumulate(person, 2, lbo);
        System.out.println(" getAndAccumulate：" + getAccumulate);
        System.out.println(" getAndAccumulate after：" + ATOMIC_LONG_FIELD_UPDATER.get(person));
        long accumulateGet = ATOMIC_LONG_FIELD_UPDATER.accumulateAndGet(person, 2, lbo);
        System.out.println(" accumulateAndGet：" + accumulateGet);
    }
}
