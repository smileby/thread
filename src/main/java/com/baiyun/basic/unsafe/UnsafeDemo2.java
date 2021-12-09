package com.baiyun.basic.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

/**
 * @author: BaiYun
 * @Date： 2021/12/9 11:30
 * @Description： Unsafe获取方式二
 */
public class UnsafeDemo2 {

    private static final Unsafe THE_UNSAFE;

    static{
        try {
            final PrivilegedExceptionAction<Unsafe> action = new PrivilegedExceptionAction<Unsafe>() {
                public Unsafe run() throws Exception {
                    Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
                    theUnsafe.setAccessible(true);
                    return (Unsafe) theUnsafe.get(null);
                }
            };
            THE_UNSAFE = AccessController.doPrivileged(action);
        }
        catch (Exception e){
            throw new RuntimeException("Unable to load unsafe", e);
        }
    }

    public static void main(String[] args) {

    }
}
