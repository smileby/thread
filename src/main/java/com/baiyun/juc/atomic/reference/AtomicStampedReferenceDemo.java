package com.baiyun.juc.atomic.reference;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author: BaiYun
 * @Date： 2021/12/7 11:33
 * @Description：
 *
 *
 */
public class AtomicStampedReferenceDemo {

    static AtomicStampedReference asr = new AtomicStampedReference("zhangsan", 1);

    public static void main(String[] args) {

    }
}
