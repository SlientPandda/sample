package com.wuhao.lock.demo;/**
 *
 */

import sun.misc.Cleaner;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 *@ClassName PhantomReferenceDemo
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/11/15 10:57
 *@Version 1.0
 **/
public class PhantomReferenceDemo {
    public static void main(String[] agrs){
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue= new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1, referenceQueue);

        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("=========");

        o1 = null;
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

    }
}
