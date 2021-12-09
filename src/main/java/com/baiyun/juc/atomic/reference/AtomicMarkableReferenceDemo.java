package com.baiyun.juc.atomic.reference;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author: BaiYun
 * @Date： 2021/12/7 11:33
 * @Description： AtomicMarkableReference 用于标记一个对象的引用，通过静态内部类Pair的方式记录
 *
 *  AtomicMarkabelReference和AtomicStampledReference一样，都可以解决ABA的问题
 *  不同的是AtomicMarkableReference是通过boolean类型来表示引用是否被修改
 *  AtomicStampledReference是通过int类型的版本号类标记引用是否被修改
 *
 *  通常在业务逻辑中，开发者一般只关心引用是否被修改，并不会关心引用被修改了几次
 *
 *
 */
public class AtomicMarkableReferenceDemo {
    static final Object INIT_REF = new Object();
    static AtomicMarkableReference<Object> amr = new AtomicMarkableReference(INIT_REF, false);

    public static void main(String[] args) {
        System.out.println("初始化的引用：");
        Object initRef = amr.getReference();
        System.out.println(initRef);
        System.out.println("初始化的标记");
        boolean initMark = amr.isMarked();
        System.out.println(initMark);

        Object newRef = new Object();
        System.out.println("修改引用值开始...");
        boolean a = amr.compareAndSet(initRef, newRef, true, false);
        if(!a){
            System.out.println("           Mark不一致，修改引用值失败");
        }
        boolean b = amr.compareAndSet(newRef, initRef, false,true);
        if(!a){
            System.out.println("           引用不一致，修改引用值失败");
        }
        boolean c = amr.compareAndSet(initRef, newRef, false,true);
        if(c){
            System.out.println("           引用一致，Mark一致，修改引用值成功");
        }
        System.out.println("最新的引用值：" + amr.getReference());
        System.out.println("最新的Mark值：" + amr.isMarked());

        // 原子的设置新的标记值，前提是引用值保持不变
        System.out.println("修改Mark值");
        amr.attemptMark(newRef, false);
        System.out.println("attemptMark后，最新的引用值：" + amr.getReference());
        System.out.println("attemptMark后，最新的Mark值：" + amr.isMarked());

        // 新引用值和新标记值只要有一个跟当前值不一样，就进行更新
        amr.set(initRef, initMark);
        System.out.println("set后,最新的引用值：" + amr.getReference());
        System.out.println("set后,最新的Mark值：" + amr.isMarked());
        amr.set(initRef, true);
        System.out.println("set后,最新的引用值：" + amr.getReference());
        System.out.println("set后,最新的Mark值：" + amr.isMarked());

    }
}
