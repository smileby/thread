package com.baiyun.juc.atomic.array;

import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @author: BaiYun
 * @Date： 2021/12/7 11:32
 * @Description： 基于原子的对象引用数组
 */
public class AtomicReferenceArrayDemo {

    private static AtomicReferenceArray atomicReferenceArray = new AtomicReferenceArray(10);

    public static void main(String[] args) {
        atomicReferenceArray.set(0, new Student("张三", 20));
    }

    static class Student{
        private String name;
        private int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return age == student.age && Objects.equals(name, student.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }


}
