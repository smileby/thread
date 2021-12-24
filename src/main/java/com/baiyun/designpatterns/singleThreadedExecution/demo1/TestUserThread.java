package com.baiyun.designpatterns.singleThreadedExecution.demo1;

/**
 * @author: BaiYun
 * @Date： 2021/12/24 15:12
 * @Description：
 */
public class TestUserThread {
    public static void main(String[] args) {
        System.out.println("Testing Gate, hit CTRL+C to exit.");
        Gate gate = new Gate();
        new UserThread(gate, "Alice", "Alaska").start();
        new UserThread(gate, "Bobby", "Brazil").start();
        new UserThread(gate, "Chris", "Canada").start();
    }
}
