package com.baiyun.juc.atomic.reference;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
     private static final Unsafe unsafe = Unsafe.getUnsafe();
     private static final long valueOffset;

     static {
         try {
            valueOffset = unsafe.objectFieldOffset(AtomicReference.class.getDeclaredField("value"));
         } catch (Exception ex) {
            throw new Error(ex);
         }
     }
     private volatile V value;

    Unsafe 是 sun.misc 包下面的类，AtomicReference 主要是依赖于 sun.misc.Unsafe 提供的一些 native 方法保证操作的原子性。
    Unsafe 的 objectFieldOffset 方法可以获取成员属性在内存中的地址相对于对象内存地址的偏移量。这个偏移量也就是 valueOffset ，
            说得简单点就是找到这个变量在内存中的地址，便于后续通过内存地址直接进行操作。
    value 就是 AtomicReference 中的实际值，因为有 volatile ，这个值实际上就是内存值。
    不同之处就在于 AtomicInteger 是对整数的封装，而 AtomicReference 则对应普通的对象引用。也就是它可以保证你在修改对象引用时的线程安全性。
 */
public class AtomicReferenceDemo {

    private static AtomicReference<Student> atomicReference = new AtomicReference();
    public static void main(String[] args) {
        System.out.println("使用get、set方法");
        Student zhangsan = new Student("zhangsan", 12);
        atomicReference.set(zhangsan);
        System.out.println(atomicReference.get());

        System.out.println("使用lazyset，该方法设置值不一定是实时的");
        Student lisi = new Student("lisi", 15);
        atomicReference.lazySet(lisi);
        System.out.println(atomicReference.get());

        System.out.println("CAS自旋，如果当前为lisi，则替换为zhangsan");
        if (atomicReference.compareAndSet(lisi, zhangsan)){
            System.out.println(atomicReference.get());
        }
        System.out.println("CAS自旋（weakCompareAndSet），如果当前为zhangsan，则替换为lisi");
        if(atomicReference.weakCompareAndSet(zhangsan, lisi)){
            System.out.println(atomicReference);
        }
        System.out.println("获取当前值，并更新为张三");
        System.out.println(atomicReference.getAndSet(zhangsan));
        System.out.println(atomicReference.get());

        System.out.println("获取当前值，并且更新对象");
        /*
            UnaryOperator<Student> uo = o -> {
                o.setAge(14);
                return o;
            };
            getAndUpdate 方法只能进行整个对象的替换，修改某个属性的话，会导致对象值的直接更新。返回与预期不一致
         */
        // 算也不算是个bug
        UnaryOperator<Student> uo = o -> {return new Student("王五", 25);};
        System.out.println(atomicReference.getAndUpdate(uo)); // 返回更新前的引用
        System.out.println(atomicReference.get());

        System.out.println("将年龄更新为21, 并获取更新后的值");
        UnaryOperator<Student> uo1 = o -> {return new Student("zhaoliu", 30);};
        System.out.println(atomicReference.updateAndGet(uo1)); // 后者返回更新后的引用。

        //  u : 当前定义的automic值， v 更改当前atomic值的变量
        System.out.println("使用一个新值原子的更新当前值");
        BinaryOperator<Student> accumulatorFunction = (u, v) -> {
            u.setAge(v.getAge());
            return u;
        };
        System.out.println(atomicReference.accumulateAndGet(lisi, accumulatorFunction));



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