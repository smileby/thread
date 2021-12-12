package com.baiyun.basic.ticket;

/**
 * @author baiyun
 * @date 2021-12-12-14:37
 *
 * 简易的卖票程序，这种实现相当于每个线程都要卖50张票，并没有实现变量共享
 *
 */
public class TicketDemo1 {

    static class Ticket extends Thread{
        private static final int TICKET_COUNT = 50;
        private int CURRENT_COUNT = 1;
        private String name;


        public Ticket(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while(CURRENT_COUNT <= TICKET_COUNT){
                System.out.println(name + "卖出第" + CURRENT_COUNT + "号票");
                CURRENT_COUNT++;
            }
        }
    }

    public static void main(String[] args) {
        new Ticket("一号窗口").start();
        new Ticket("二号窗口").start();
        new Ticket("三号窗口").start();
    }
}
