package com.baiyun.designpatterns.Observable;

/**
 * Subject
 * @author baiyun
 * @date 2021-12-14-21:51
 */
public abstract class ObserverRunnable implements Runnable{

    private final LifecycleListener lifeCycleListener;

    public ObserverRunnable(final LifecycleListener lifeCycleListener) {
        this.lifeCycleListener = lifeCycleListener;
    }

    public void notifyChange(final ThreadEvent event){
        lifeCycleListener.onEvent(event);
    }

    public enum THREAD_STATE{
        START, RUNNING, DONE, ERROR;
    }

    public static class ThreadEvent{
        private THREAD_STATE state;
        private Thread thread;
        private Throwable cause;

        public ThreadEvent(THREAD_STATE state, Thread thread, Throwable cause) {
            this.state = state;
            this.thread = thread;
            this.cause = cause;
        }

        public THREAD_STATE getState() {
            return state;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getCause() {
            return cause;
        }
    }
}
