package com.baiyun.juc.atomic.reference;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author: BaiYun
 * @Date： 2021/12/7 11:33
 * @Description：
 *      AtomicStmapedReference它的内部不仅维护了一个引用值，还维护了一个时间戳（我们把它称为一个时间戳，实际上它可以是任何一个整数
 *      它使用整数来表示一个状态值）。当AtomicStampedReference内部的引用值更新时，我们除了要更新引用值，还必须要同步更新它的时间戳
 *      。当要重新设置引用值的时候，它的引用值和时间戳必须满足期望值，才能够成功写入。因此，即使引用被反复读写，然后写回原来的引用值，
 *      只要时间戳发生变化，他就能防止不正确的写入。
 *      demo用来掩饰AtomicStampedReference的API方法
 */
public class AtomicStampedReferenceDemo {

    static AtomicStampedReference asr = new AtomicStampedReference("zhangsan", 1);

    public static void main(String[] args) {
        System.out.println("设置一个新的引用和时间戳");
        String lisi = "李四";
        asr.set(lisi, 2);

        System.out.println("获取当前的引用");
        System.out.println(asr.getReference());

        System.out.println("获取当前的时间戳");
        System.out.println(asr.getStamp());

        System.out.println("compareAndSet更新引用和时间戳");
        String wangwu = "王五";
        System.out.println(asr.compareAndSet(lisi, wangwu, 2, asr.getStamp() + 1));

        System.out.println("weakCompareAndSet更新引用和时间戳");
        System.out.println(asr.weakCompareAndSet(wangwu, lisi, 3, asr.getStamp() + 1));

        System.out.println("更新一个期望引用值的版本号");
        System.out.println(asr.attemptStamp(lisi, asr.getStamp() + 1));

        System.out.println("最新的时间戳: " + asr.getStamp());

        System.out.println("通过一个时间戳数组获取一个引用，且数组的第一个元素将返回当前版本时间戳");
        int[] stamps = {1,2};
        System.out.println(asr.get(stamps));
        System.out.println(stamps[0]);
    }
}
