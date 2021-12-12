package com.baiyun.basic.singleton;

import java.util.stream.IntStream;

/**
 * 懒汉模式 ：线程不安全，多线程下可能new多个Test类。
 * 单例模式--懒汉式
 * 问题： 实现了懒加载，但是可能会出现线程安全的问题，产生多个实例
 * @author baiyun
 */
public class Singleton_2 {
    private static Singleton_2 instance;
    private Singleton_2(){
        // empty
    }
    public static Singleton_2 getInstance(){
        if(null == instance){
            instance = new Singleton_2();
        }
        return instance;
    }

    public static void main(String[] args){
        IntStream.rangeClosed(1, 1000).forEach(i -> new Thread(String.valueOf(i)){
            @Override
            public void run() {
                System.out.println(Singleton_2.getInstance());
            }
        }.start());
        IntStream.rangeClosed(1, 1000).forEach(i -> new Thread(String.valueOf(i)){
            @Override
            public void run() {
                System.out.println(Singleton_2.getInstance());
            }
        }.start());
        IntStream.rangeClosed(1, 1000).forEach(i -> new Thread(String.valueOf(i)){
            @Override
            public void run() {
                System.out.println(Singleton_2.getInstance());
            }
        }.start());
    }
}
