package com.baiyun.designpatterns.singleThreadedExecution.demo1;

import java.util.concurrent.TimeUnit;

/**
 * @author: BaiYun
 * @Date： 2021/12/24 15:11
 * @Description：
 */
public class UserThread extends Thread{
    private final Gate gate;
    private final String myname;
    private final String myaddress;
    public UserThread(Gate gate, String myname, String myaddress) {
        this.gate = gate;
        this.myname = myname;
        this.myaddress = myaddress;
    }
    @Override
    public void run() {
        System.out.println(myname + " BEGIN");
        while (true) {
            gate.pass(myname, myaddress);
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
