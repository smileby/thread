package com.baiyun.juc.atomic.reference;

import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 * @author: BaiYun
 * @Date： 2021/12/7 11:33
 * @Description：
 */
public class AtomicReferenceArrayDemo {

    private static AtomicReferenceArray<Student> studentAtomicReferenceArray = new AtomicReferenceArray<Student>(10);
    public static void main(String[] args) {
        System.out.println("设置值");
        Student zhangsan = new Student("张三", 19);
        studentAtomicReferenceArray.set(0, zhangsan);
        Student lisi = new Student("李四", 20);
        studentAtomicReferenceArray.lazySet(1, lisi);
        System.out.println("获取坐标为0的值");
        System.out.println(studentAtomicReferenceArray.get(0));
        System.out.println("获取长度");
        System.out.println(studentAtomicReferenceArray.length());
        System.out.println("比较并交换0号坐标的值");
        System.out.println(studentAtomicReferenceArray.compareAndSet(0, zhangsan, new Student("王五", 20)));
        System.out.println(studentAtomicReferenceArray.get(0));
        System.out.println("比较并交换0号坐标的值");
        System.out.println(studentAtomicReferenceArray.weakCompareAndSet(1, lisi, new Student("赵六", 22)));
        System.out.println(studentAtomicReferenceArray.get(1));

        System.out.println("获取旧值设置新值");
        System.out.println(studentAtomicReferenceArray.getAndSet(1, new Student("王五", 20)));
        System.out.println(studentAtomicReferenceArray.get(1));

        System.out.println("获取旧值，更新新值");
        UnaryOperator<Student> uf = o -> {return new Student("张齐", 26);};
        System.out.println(studentAtomicReferenceArray.getAndUpdate(1, uf));
        System.out.println(studentAtomicReferenceArray.get(1));

        System.out.println("更新旧值，获取新值");
        UnaryOperator<Student> uf1 = o -> {return new Student("赵八", 26);};
        System.out.println(studentAtomicReferenceArray.updateAndGet(1, uf1));

        System.out.println("使用一个新值更新当前值，然后获取");
        BinaryOperator<Student> accumulatorFunction = (u, v) -> {v.setAge(u.getAge() + 1); return v;};
        System.out.println(studentAtomicReferenceArray.accumulateAndGet(1, new Student("张山", 6), accumulatorFunction));

        System.out.println("获取当前值，然后使用一个新值更新当前值");
        BinaryOperator<Student> accumulatorFunction1 = (u, v) -> {v.setAge(u.getAge() + 1); return v;};
        System.out.println(studentAtomicReferenceArray.getAndAccumulate(1, new Student("张si", 90), accumulatorFunction1));
        System.out.println(studentAtomicReferenceArray.get(1));

    }

    static class Student{
        private String name;
        private int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age='" + age + '\'' +
                    '}';
        }
    }
}
