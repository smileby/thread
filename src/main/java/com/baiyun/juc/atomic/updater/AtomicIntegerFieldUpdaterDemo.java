package com.baiyun.juc.atomic.updater;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

/**
 * @author: BaiYun
 * @Date： 2021/12/7 11:34
 * @Description：
 *   Atomic可以对指定类指定的volatile修饰的int变量进行原子的更新。
 *
 */
public class AtomicIntegerFieldUpdaterDemo {

    static class Student {
        String name;
        volatile int score;
    }

    private static final AtomicIntegerFieldUpdater<Student> ATOMIC_INTEGER_FIELD_UPDATER =
            AtomicIntegerFieldUpdater.newUpdater(Student.class, "score");

    public static void main(String[] args) {

        Student student = new Student();
        // get和set方法都是抽象方法，这个是放在内部类中实现的方法。
        ATOMIC_INTEGER_FIELD_UPDATER.set(student, 10);
        ATOMIC_INTEGER_FIELD_UPDATER.lazySet(student, 11);
        int i = ATOMIC_INTEGER_FIELD_UPDATER.get(student);
        System.out.println("get: "  + i);

        // 比较设置方法
        boolean b = ATOMIC_INTEGER_FIELD_UPDATER.compareAndSet(student, 11, 12);
        System.out.println("compareAndSet: " + b);
        boolean b1 = ATOMIC_INTEGER_FIELD_UPDATER.weakCompareAndSet(student, 12, 11);
        System.out.println("weakCompareAndSet: " + b1);

        // 获取设置方法
        int andSet = ATOMIC_INTEGER_FIELD_UPDATER.getAndSet(student, 14);
        System.out.println("getAndSet:" + andSet);

        // 获取增加方法
        int getAdd = ATOMIC_INTEGER_FIELD_UPDATER.getAndAdd(student, 1);
        System.out.println("getAndAdd: " + getAdd);
        System.out.println("getAndAdd after: " + ATOMIC_INTEGER_FIELD_UPDATER.get(student));
        int addGet = ATOMIC_INTEGER_FIELD_UPDATER.addAndGet(student, 1);
        System.out.println("addAndGet: " + addGet);


        // 获取自增方法
        int getIncrement = ATOMIC_INTEGER_FIELD_UPDATER.getAndIncrement(student);
        System.out.println(" getAndIncrement：" + getIncrement);
        System.out.println(" getAndIncrement after：" + ATOMIC_INTEGER_FIELD_UPDATER.get(student));
        int incrementGet = ATOMIC_INTEGER_FIELD_UPDATER.incrementAndGet(student);
        System.out.println(" incrementAndGet：" + incrementGet);

        // 获取自减
        int getDecrement = ATOMIC_INTEGER_FIELD_UPDATER.getAndDecrement(student);
        System.out.println(" getAndDecrement：" + getDecrement);
        System.out.println(" getAndDecrement after：" + ATOMIC_INTEGER_FIELD_UPDATER.get(student));
        int decrementGet = ATOMIC_INTEGER_FIELD_UPDATER.decrementAndGet(student);
        System.out.println(" decrementAndGet：" + decrementGet);

        // 获取更新方法
        IntUnaryOperator iuo = (u) -> u * 2;
        int getUpdate = ATOMIC_INTEGER_FIELD_UPDATER.getAndUpdate(student, iuo);
        System.out.println(" getAndUpdate：" + getUpdate);
        System.out.println(" getAndUpdate after：" + ATOMIC_INTEGER_FIELD_UPDATER.get(student));
        int updateGet = ATOMIC_INTEGER_FIELD_UPDATER.updateAndGet(student, iuo);
        System.out.println(" updateAndGet：" + updateGet);

        System.out.println();

        // 获取累加方法  score = 64 + 2
        IntBinaryOperator ibo = (u, x) -> u + x;
        int getAccumulate = ATOMIC_INTEGER_FIELD_UPDATER.getAndAccumulate(student, 2, ibo);
        System.out.println(" getAndAccumulate：" + getAccumulate);
        System.out.println(" getAndAccumulate after：" + ATOMIC_INTEGER_FIELD_UPDATER.get(student));
        int accumulateGet = ATOMIC_INTEGER_FIELD_UPDATER.accumulateAndGet(student, 2, ibo);
        System.out.println(" accumulateAndGet：" + accumulateGet);


    }
}
