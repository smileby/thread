package com.baiyun.designpatterns.Observable;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author baiyun
 * @date 2021-12-14-22:01
 */
public class ThreadLifecycleObserver implements LifecycleListener {

    private static final Object LOCK = new Object();

    public void concurrentQuery(List<String> ids){
        if(null == ids || ids.isEmpty()){
            return ;
        }
        ids.stream().forEach(id -> new Thread(new ObserverRunnable(this) {
            @Override
            public void run() {
                try {
                    super.notifyChange(new ThreadEvent(THREAD_STATE.START, Thread.currentThread(), null));
                    System.out.println("id: "+ id + "开始执行查询。");
                    super.notifyChange(new ThreadEvent(THREAD_STATE.RUNNING, Thread.currentThread(), null));
                    System.out.println("id: "+ id + "查询执行中.....");
//                    int x = 1/0;
                    TimeUnit.SECONDS.sleep(10);
                    super.notifyChange(new ThreadEvent(THREAD_STATE.DONE, Thread.currentThread(), null));
                    System.out.println("id: "+ id + "查询完毕。");
                } catch (Exception e) {
                    super.notifyChange(new ThreadEvent(THREAD_STATE.ERROR, Thread.currentThread(), e));
                    System.out.println("id: "+ id + "查询异常。");
                }
            }
        }, id).start());
    }

    @Override
    public void onEvent(ObserverRunnable.ThreadEvent event) {
        synchronized (LOCK){
            System.out.println("当前线程" + event.getThread().getName() +", 状态：" + event.getState().toString());
            if(null != event.getCause()){
                // 可以做异常处理、保存数据库、重试。。。。
                event.getCause().printStackTrace();
            }
        }
    }
}
