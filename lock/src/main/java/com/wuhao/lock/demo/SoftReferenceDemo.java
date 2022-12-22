package com.wuhao.lock.demo;/**
 *
 */

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 *@ClassName SoftReferenceDemo
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/11/10 19:52
 *@Version 1.0
 *
 * 软引用：
 * 内存够用则保留，不够用就删除
 **/
public class SoftReferenceDemo {
    private static final int _1MB = 1024 * 1024;

    public static void softRef_Memory_Enough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();

        System.out.println(o1);
        System.out.println(softReference.get());
    }

    public static void softRef_Memory_NotEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;

        try {
            byte[] bytes = new byte[50 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(softReference.get());
        }
    }


    public static void main(String[] args) {
        Map<String, SoftReference<byte[]>> imageCache = new HashMap<String, SoftReference<byte[]>>();
        imageCache.put("1", new SoftReference<>(new byte[5 * _1MB]));
        imageCache.put("2", new SoftReference<>(new byte[5 * _1MB]));

        //1引用的对象已经被gc回收了，这时会报空指针错
        System.out.println(imageCache.get("1").get().toString());
        System.out.println(imageCache.get("2").get().toString());

    }
}
