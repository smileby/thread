package com.baiyun.juc.atomic.updater;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 * @author: BaiYun
 * @Date： 2021/12/7 11:34
 * @Description：
 *      一个基于反射的工具类，它能对指定类的指定的volatile引用字段进行原子更新。(注意这个字段不能是private的)
 */
public class AtomicReferenceFieldUpdaterDemo {

    static class Person{
        volatile String name;
    }
    //  参数1 ： 更新的对象类  参数2 ： 更新属性的类   参数3：属性名
    private static final AtomicReferenceFieldUpdater<Person, String> ATOMIC_REFERENCE_FIELD_UPDATER =
            AtomicReferenceFieldUpdater.newUpdater(Person.class, String.class, "name");

    public static void main(String[] args) {
        Person person = new Person();
        String zhangsan = "zhangsan";
        String lisi = "lisi";

        // get和set方法都是抽象方法，这个是放在内部类中实现的方法。
        ATOMIC_REFERENCE_FIELD_UPDATER.set(person, zhangsan);
        ATOMIC_REFERENCE_FIELD_UPDATER.lazySet(person, lisi);
        String s = ATOMIC_REFERENCE_FIELD_UPDATER.get(person);
        System.out.println("get: "  + s);

        // 比较设置方法
        boolean b = ATOMIC_REFERENCE_FIELD_UPDATER.compareAndSet(person, lisi, zhangsan);
        System.out.println("compareAndSet: " + b);
        boolean b1 = ATOMIC_REFERENCE_FIELD_UPDATER.weakCompareAndSet(person, zhangsan, lisi);
        System.out.println("weakCompareAndSet: " + b1);

        // 获取设置方法
        String andSet = ATOMIC_REFERENCE_FIELD_UPDATER.getAndSet(person, zhangsan);
        System.out.println("getAndSet:" + andSet);


        // 获取更新方法
        UnaryOperator luo = (u) -> u + "2";
        String getUpdate = ATOMIC_REFERENCE_FIELD_UPDATER.getAndUpdate(person, luo);
        System.out.println(" getAndUpdate：" + getUpdate);
        System.out.println(" getAndUpdate after：" + ATOMIC_REFERENCE_FIELD_UPDATER.get(person));
        String updateGet = ATOMIC_REFERENCE_FIELD_UPDATER.updateAndGet(person, luo);
        System.out.println(" updateAndGet：" + updateGet);

        System.out.println();

        // 获取累加方法  score = 64 + 2
        BinaryOperator<String> lbo = (u, x) -> u + x;
        String getAccumulate = ATOMIC_REFERENCE_FIELD_UPDATER.getAndAccumulate(person, "3", lbo);
        System.out.println(" getAndAccumulate：" + getAccumulate);
        System.out.println(" getAndAccumulate after：" + ATOMIC_REFERENCE_FIELD_UPDATER.get(person));
        String accumulateGet = ATOMIC_REFERENCE_FIELD_UPDATER.accumulateAndGet(person, "3", lbo);
        System.out.println(" accumulateAndGet：" + accumulateGet);
    }
}
