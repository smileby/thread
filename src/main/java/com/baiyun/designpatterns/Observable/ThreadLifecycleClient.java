package com.baiyun.designpatterns.Observable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author baiyun
 * @date 2021-12-14-22:12
 *
 *   观察者模式的多线程实现
 */
public class ThreadLifecycleClient {

    public static void main(String[] args) {
        new ThreadLifecycleObserver().concurrentQuery(Arrays.asList("1", "2"));
    }
}
