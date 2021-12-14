package com.baiyun.designpatterns;

/**
 * @author baiyun
 * @date 2021-12-14-21:10
 */
public abstract class Observer {

    protected Subject subject;

    public Observer(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    public abstract void update();
}
