package com.baiyun.basic.singleton;

/**
 * 通过holder的方式（静态内部类）
 * 保证线程安全，还能保证效率很高（不加任何锁）
 *      原因：
 *          在类的加载过程中，分为几个阶段
 *          装载： 读class文件
 *          链接； 给一些没有赋值的东西赋值，给一些static的东西初始化（这个初始化不等于第三个阶段的初始化）
 *      这个阶段的初始化，可能就是引用类型初始化null的操作
 *          初始化：进行真正的初始化
 *       static还有一个特性，在JVM加载的时候只会被运行一次，而且能严格的保证线程的执行顺序。所以说它不会被
 *      创建两次，在调用它的时候是主动加载，在使用它的class对象才会被加载
 * @author baiyun
 */
public class Singleton_6 {
    private Singleton_6(){

    }
    private static class Singleton_6_Holder {
        private static final Singleton_6 instance = new Singleton_6();
    }

    public static Singleton_6 getInstance(){
        return Singleton_6_Holder.instance;
    }
}
