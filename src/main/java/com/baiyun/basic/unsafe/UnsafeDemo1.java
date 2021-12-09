package com.baiyun.basic.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author: BaiYun
 * @Date： 2021/12/9 11:29
 * @Description： Unsafe获取方式一
 */
public class UnsafeDemo1 {

    private static Unsafe unsafe;
    private static long stateOffset;
    private volatile long state=0;

    static {
        try {

            //通过反射获取Unsafe的成员变量theUnsafe
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            //设置为内存可以取
            field.setAccessible(true);
            //获取值
            unsafe=(Unsafe)field.get(null);
            //获取偏移量
            stateOffset=unsafe.objectFieldOffset(UnsafeDemo1.class.getDeclaredField("state"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            System.err.println(e.getLocalizedMessage());
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        UnsafeDemo1 unsafeDemo1 = new UnsafeDemo1();
        System.out.println(stateOffset);
        boolean success = unsafe.compareAndSwapLong(unsafeDemo1, stateOffset, 0, 2);
        System.out.println("success "+success);
    }
}
