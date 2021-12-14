package com.baiyun.designpatterns;

/**
 * @author baiyun
 * @date 2021-12-14-21:15
 */
public class OctalObserver extends Observer {

    public OctalObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Octal String :" + Integer.toOctalString(super.subject.getState()));
    }
}
