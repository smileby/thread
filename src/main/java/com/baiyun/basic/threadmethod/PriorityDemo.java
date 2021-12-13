package com.baiyun.basic.threadmethod;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: BaiYun
 * @Date： 2021/12/13 11:30
 * @Description： 线程优先级
 *
 *  线程的执行顺序是随机执行的，如果想要控制线程执行的优先级，可以通过设置线程的优先级来实现。
 *  设置优先级以后，优先级高的，线程调度器更倾向于执行优先级高的线程，并不意味着优先级低的线程
 *  得不到执行，只是优先级低的线程执行频率较低
 *
 *  尽管JDK有10个优先级，但是一般只有MAX_PRIORITY，NORM_PRIORITY，MIN_PRIORITY 三种级别。
 */
public class PriorityDemo {
    static class SimplePriorities implements Runnable {

        private int priority;

        public SimplePriorities(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {
            Thread.currentThread().setPriority(priority);
            for (int i = 0; i < 100; i++) {
                System.out.println(this);
                if (i % 10 == 0) {
                    /*
                        如果知道一个线程已经在 run() 方法中运行的差不多了，那么它就可以给线程调度器一个提示：
                        我已经完成了任务中最重要的部分，可以让给别的线程使用CPU了。这个暗示将通过 yield() 方法作出。

                        ★★ 有一个很重要的点就是，Thread.yield() 是建议执行切换CPU，而不是强制执行CPU切换。
                        对于任何重要的控制或者在调用应用时，都不能依赖于 yield() 方法，
                        实际上， yield() 方法经常被滥用。
                     */
                    Thread.yield();
                }
            }
        }

        @Override
        public String toString() {
            return Thread.currentThread() + " " + priority;
        }
    }
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0;i < 5;i++){
            service.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        }
        service.execute(new SimplePriorities(Thread.MIN_PRIORITY));
    }
}
