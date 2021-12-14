package com.baiyun.designpatterns.Observable;

/**
 * @author baiyun
 * @date 2021-12-14-21:53
 */
public interface LifecycleListener {

    void onEvent(ObserverRunnable.ThreadEvent event);
}
