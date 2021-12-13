package com.baiyun.basic.create.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: BaiYun
 * @Date： 2021/12/13 10:40
 * @Description： TODO
 */
public class SingleThreadExecutorDemo {
    static class TestThread implements Runnable{

        public static int i = 0;

        @Override
        public void run() {
            System.out.println("start thread..." + i);
            i++;
            System.out.println("end thread ..." + i);
        }
    }
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for(int i = 0;i < 5;i++){
            service.execute(new TestThread());
        }
        service.shutdown();
    }
}
