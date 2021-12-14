package com.baiyun.designpatterns;

/**
 * @author baiyun
 * @date 2021-12-14-21:14
 */
public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("binary String : " + Integer.toBinaryString(super.subject.getState()));
    }
}
