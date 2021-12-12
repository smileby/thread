package com.baiyun.basic.singleton;

/**
 * 懒汉模式-双重锁机制+volatile：线程安全。volatile禁止了指令重排。
 * 使用volatile修饰
 *       volatile并不能保证原子性，它可以保证内存的可见性，也就是说多个线程看到的数据是同一份，
 *   保证有序性（在C++中很明显的告诉编译器，这个东西你不要给我自作多情的去优化）
 *       valatile定义的东西，在它完成之后就是真正的完成了（Singleton_4并不是这样），它是严格
 *   的遵循happens-before原则，（它修饰的变量，在读之前必须要有写的操作，也就保证了构造函数中的
 *   所有初始化逻辑都执行完成了）
 * @author baiyun
 */
public class Singleton_5 {
    private static volatile Singleton_5 instance;
    private Singleton_5(){
        // empty
    }
    public static Singleton_5 getInstance(){
        if(null == instance){
            synchronized(Singleton_5.class){
                if(null == instance){
                    instance = new Singleton_5();
                }
            }
        }
        return Singleton_5.instance;
    }
}
