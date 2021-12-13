package com.baiyun.basic.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: BaiYun
 * @Date： 2021/12/13 14:25
 * @Description：
 *
 * 由于线程的本质，使你不能捕获从线程中逃逸的异常，一旦异常逃出任务的run 方法，它就会向外传播到控制台，
 * 除非你采取特殊的步骤捕获这种错误的异常，在 Java5 之前，你可以通过线程组来捕获，但是在 Java5 之后，
 * 就需要用 Executor 来解决问题，因为线程组不是一次好的尝试。
 *
 * 下面的任务会在 run 方法的执行期间抛出一个异常，并且这个异常会抛到 run 方法的外面，而且 main 方法无法对它进行捕获
 */
public class ExceptionThreadDemo1 implements Runnable{

    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        try {
            ExecutorService service = Executors.newCachedThreadPool();
            service.execute(new ExceptionThreadDemo1());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
