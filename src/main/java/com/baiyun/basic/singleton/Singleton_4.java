package com.baiyun.basic.singleton;

/**
 * 懒汉模式-双重锁机制 ：线程不安全。由于指令重排。
 * 懒汉式：
 *      使用double-check的方式，解决了性能问题，产生单个实例, 但是由于指令重排的问题，导致线程不安全
 *
 * 问题：
 *      可能会引起空指针异常
 *          在一个线程进行new操作后，会立即在堆内存空间中开辟一块内存区域，但是如果在单例对象的构造器中
 *      有复杂的初始化逻辑，在并没有构造完成的情况下就会直接返回，另外一个线程在调用时，单例对象不为null，
 *      直接返回，当使用返回的单例对象时，可能其中的一些引用或初始化逻辑还没有完成，就会导致出现空指针异常的问题
 *          出现这种情况的原因是：在java在编译的过程中进行了一些优化和指令重排序的操作。编译器必须要做一些优化
 *      来帮助我们提高一些代码的性能，在运行阶段也会有一定的优化，程序计数器给CPU的时候，不一定会按照代码
 *      编写的顺序进行执行，JVM规范只规定了：最终的效果符合程序运行的预期即可，在这期间可以做一些编译期的优化，
 *      运行期的优化。
 * @author baiyun
 */
public class Singleton_4 {
    private static Singleton_4 instance;
    private Singleton_4(){
        // empty
    }

    /**
     * instance = new Singleton_4();
     * 这句，这并非是一个原子操作，事实上在 JVM 中这句话大概做了下面 3 件事情。
     * 　　1. 给 instance 分配堆内存(Singleton_4 对象)
     * 　　2. 调用 Singleton_4 的构造函数来初始化成员变量，形成实例
     * 　　3. 将instance 指针 指向分配的内存空间（执行完这步 singleton才是非 null了）。
     * 正常执行顺序：1->2->3，由于操作2和操作3没有依赖性（操作1和操作3有依赖性），
     * 可能发生指令重排，可能的执行顺序为：1->3->2。
     * 回到代码，当操作1,3执行后，instnce指针是不为null了，
     * 此时，另一个线程执行 if(instance == null) 就会不成立，直接返回，
     * 而此时，Single的构造还可能未执行，会引发严重数据错误！！！！
     */
    public static Singleton_4 getInstance(){
        if(null == instance){
            synchronized(Singleton_4.class){
                if(null == instance){
                    instance = new Singleton_4();
                }
            }
        }
        return Singleton_4.instance;
    }
}
