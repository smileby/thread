package com.baiyun.basic.threadFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author: BaiYun
 * @Date： 2021/12/13 13:56
 * @Description：
 */
public class JDKThreadFactory {

    public static void main(String[] args) {

        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        ThreadFactory threadFactory1 = Executors.privilegedThreadFactory();

    }
}
