package com.baiyun.designpatterns;

/**
 * @author baiyun
 * @date 2021-12-14-21:17
 * 观察者设计模式
 */
public class ObserverClient {
    public static void main(String[] args) {
        Subject subject = new Subject();
        new BinaryObserver(subject);
        new OctalObserver(subject);

        subject.setState(10);
        System.out.println("=------------=====================");
        subject.setState(10);
        System.out.println("=------------=====================");
        subject.setState(15);
        System.out.println("=------------=====================");
    }
}
