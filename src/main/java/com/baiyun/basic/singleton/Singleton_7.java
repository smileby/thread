package com.baiyun.basic.singleton;

/**
 * 枚举单例：线程安全。最优解。
 * 枚举类： 构造器一定是私有的，类一定是final修饰的
 * 枚举单例：
 *
 * @author baiyun
 * @date 2020-12-07-13:52
 */
public class Singleton_7 {

    private Singleton_7(){
    }

    private enum Singleton_Enum{
        INSTANCE;
        private final Singleton_7 instance;
        Singleton_Enum(){
            instance = new Singleton_7();
        }
        public Singleton_7 getInstance(){
            return instance;
        }
    }

    public static Singleton_7 getInstance(){
        return Singleton_Enum.INSTANCE.getInstance();
    }
}
