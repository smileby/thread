package com.baiyun.basic.singleton;

/**
 * 懒汉模式-加锁 ：线程安全，方法上加锁严重影响性能。第二次直接取instance值都加锁了
 * 懒汉式：
 *  实现了懒加载，也解决了线程安全问题，
 *  问题：
 *      性能不好，需要抢锁
 * @author baiyun
 */
public class Singleton_3 {
    private static Singleton_3 instance;
    private Singleton_3(){
        // empty
    }
    public synchronized static Singleton_3 getInstance(){
        if(null == instance){
            instance = new Singleton_3();
        }
        return Singleton_3.instance;
    }
}
