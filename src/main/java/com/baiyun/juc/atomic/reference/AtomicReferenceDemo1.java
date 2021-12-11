package com.baiyun.juc.atomic.reference;

import java.util.concurrent.TimeUnit;

/**
 * @author: BaiYun
 * @Date： 2021/12/8 14:01
 * @Description：
 *      这个demo演示 对象在修改时的线程不安全性,并且使用两种方式解决了这个不安全的问题
 *      1、使用Synchronized关键字进行同步； 2、使用AtomicReference的CAS机制来保证对象修改的安全性
 */
public class AtomicReferenceDemo1 {

    private static volatile BankCard bankCard = new BankCard("baiyun",100);


    public static void main(String[] args) {

        for(int i = 0;i < 10;i++){
            new Thread(() -> {
                // 此处不使用synchronized进行同步，即使BankCard使用了volatile关键字，也不能保证线程的安全。
//                synchronized (AtomicReferenceDemo1.class) {
                    // 先读取全局的引用
                    final BankCard card = bankCard;
                    // 构造一个新的账户，存入一定数量的钱
                    BankCard newCard = new BankCard(card.getAccountName(), card.getMoney() + 100);
                    System.out.println(newCard);
                    // 最后把新的账户的引用赋给原账户
                    bankCard = newCard;
                    try {
                        TimeUnit.MICROSECONDS.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                }
            }).start();
        }
    }

    static class BankCard {

        private final String accountName;
        private final int money;

        // 构造函数初始化 accountName 和 money
        public BankCard(String accountName,int money){
            this.accountName = accountName;
            this.money = money;
        }
        // 不提供任何修改个人账户的 set 方法，只提供 get 方法
        public String getAccountName() {
            return accountName;
        }
        public int getMoney() {
            return money;
        }
        // 重写 toString() 方法， 方便打印 BankCard
        @Override
        public String toString() {
            return "BankCard{" +
                    "accountName='" + accountName + '\'' +
                    ", money='" + money + '\'' +
                    '}';
        }
    }
}
