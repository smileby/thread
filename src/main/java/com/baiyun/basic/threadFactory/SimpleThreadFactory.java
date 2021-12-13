package com.baiyun.basic.threadFactory;

import java.util.concurrent.ThreadFactory;

/**
 * @author: BaiYun
 * @Date： 2021/12/13 13:50
 * @Description： 按需要创建线程的对象，使用ThreadFactory替换了Thread和Runnable接口的硬链接，
 * 程序能够使用特殊的线程子类，实现线程的优先级、名称、后台线程状态及线程组等
 *
 * ThreadFactory是一个接口，它只有一个方法就是创建线程的方法
 * */
public class SimpleThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r);
    }
}
