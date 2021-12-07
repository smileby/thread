package com.baiyun.juc.atomic.basic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;

/**
 * @author: BaiYun
 * @Date： 2021/12/7 9:59
 * @Description： AtomicInteger 可用方法与 AtomicLong 一致
 */
public class AtomicIntegerDemo {

    private static AtomicInteger atomicInteger = new AtomicInteger(2);

    public static void main(String[] args) {
        System.out.println("先获取再自增");
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.get());
        System.out.println("先获取再自减");
        System.out.println(atomicInteger.getAndDecrement());
        System.out.println(atomicInteger.get());
        System.out.println("先自增再获取");
        System.out.println(atomicInteger.incrementAndGet());
        System.out.println(atomicInteger.get());
        System.out.println("先自减再获取");
        System.out.println(atomicInteger.decrementAndGet());
        System.out.println(atomicInteger.get());

        System.out.println("先修改再获取");
        System.out.println(atomicInteger.updateAndGet(value -> value * 2));
        System.out.println("先获取再修改");
        System.out.println(atomicInteger.getAndUpdate(value -> value * 2));
        System.out.println(atomicInteger.get());

        // IntBinaryOperator 是包含两个参数的无副作用函数 u : 当前定义的automic值， v 更改当前atomic值的变量
        System.out.println("原子的更新当前值，并获取结果");
        int x = 1;
        IntBinaryOperator operator = (u, v) -> u / v;
        System.out.println(atomicInteger.accumulateAndGet(x, operator));
        System.out.println(atomicInteger.get());

        System.out.println("获取结果，并原子的更新当前值");
        int z = 2;
        System.out.println(atomicInteger.getAndAccumulate(z, operator));
        System.out.println(atomicInteger.get());

        System.out.println("增加一个值，然后获取");
        System.out.println(atomicInteger.addAndGet(5));
        System.out.println(atomicInteger.get());
        System.out.println("获取一个值，然后增加一个值");
        System.out.println(atomicInteger.getAndAdd(5));
        System.out.println(atomicInteger.get());
        System.out.println("先获取再重新set一个值");
        System.out.println(atomicInteger.getAndSet(5));
        System.out.println(atomicInteger.get());

        System.out.println("符合预期值（compareAndSet），则更新为新值");
        System.out.println(atomicInteger.compareAndSet(5, 1));
        System.out.println(atomicInteger.get());
        /*
            https://blog.csdn.net/lzcaqde/article/details/80868854
            https://oomake.com/question/50411
            weakCompareAndSet 和 compareAndSet 都使用了 unsafe.compareAndSwapInt  ？？？？？
         */
        System.out.println("符合预期值（weakCompareAndSet），则更新为新值");
        System.out.println(atomicInteger.weakCompareAndSet(1, 5));
        System.out.println(atomicInteger.get());


        /*
            set()和volatile具有一样的效果(能够保证内存可见性，能够避免指令重排序)
            但是使用lazySet不能保证其他线程能立刻看到修改后的值(有可能发生指令重排序)
            lazySet比set()具有性能优势，但是使用场景很有限
            set()会立刻修改旧值，别的线程可以立刻看到更新后的值；而lazySet不会立刻(但是最终会)修改旧值，
            别的线程看到新值的时间会延迟一些.

            该方法没有返回值
         */
        System.out.println("使用set更新值");
        atomicInteger.set(50);
        System.out.println(atomicInteger.get());
        System.out.println("使用lazySet更新值");
        atomicInteger.lazySet(100);
        System.out.println(atomicInteger.get());

    }

}
