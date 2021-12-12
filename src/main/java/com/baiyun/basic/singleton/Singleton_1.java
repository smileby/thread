package com.baiyun.basic.singleton;

/**
 * 饿汉模式 ：线程安全，但是当类加载时，就new实例对象了，不符合懒加载模式。
 * 单例模式--饿汉式
 * 问题： 不能进行懒加载
 * @author baiyun
 */
public class Singleton_1 {
    private static Singleton_1 instance = new Singleton_1();
    private Singleton_1(){
        // empty
    }
    public static Singleton_1 getInstance(){
        return instance;
    }
}
