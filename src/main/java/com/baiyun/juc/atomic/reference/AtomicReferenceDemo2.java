package com.baiyun.juc.atomic.reference;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: BaiYun
 * @Date： 2021/12/8 14:26
 * @Description：
 */
public class AtomicReferenceDemo2 {

    private static AtomicReference<BankCard> atomicReference = new AtomicReference(new BankCard("baiyun",100));

    public static void main(String[] args) {
        for(int i = 0;i < 10;i++){
            new Thread(() -> {
//                while (true){
                BankCard bankCard = atomicReference.get();
                BankCard newBackCard = new BankCard(bankCard.getAccountName(), bankCard.getMoney() + 100);
                // 使用CAS乐观锁进行替换
                if(atomicReference.compareAndSet(bankCard, newBackCard)){
                    /*
                        可以看到，有一些输出是乱序执行的，出现这个原因很简单，
                        有可能在输出结果之前，进行线程切换，然后打印了后面线程的值，
                        然后线程切换回来再进行输出，但是可以看到，没有出现银行卡金额相同的情况。
                     */
                    System.out.println(newBackCard);
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                }

            }).start();
        }


    }

}
class BankCard {

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

