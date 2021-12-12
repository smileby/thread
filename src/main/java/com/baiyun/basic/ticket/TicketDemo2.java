package com.baiyun.basic.ticket;

/**
 * @author baiyun
 * @date 2021-12-12-14:48
 *
 * 这种方式实现了变量共享的问题，但是出现了线程安全的问题
 */
public class TicketDemo2 {

    static class TicketWindow implements Runnable{
        private static final int TICKET_COUNT = 50;
        private int CURRENT_COUNT = 1;

        @Override
        public void run() {
            while(CURRENT_COUNT <= TICKET_COUNT){
                System.out.println(Thread.currentThread().getName() + "卖出第" + CURRENT_COUNT + "号票");
                CURRENT_COUNT++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        TicketWindow ticketWindow = new TicketWindow();
        new Thread(ticketWindow, "一号窗口").start();
        new Thread(ticketWindow, "二号窗口").start();
        new Thread(ticketWindow, "三号窗口").start();
    }
}
