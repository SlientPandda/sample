package com.wuhao.lock.demo;/**
 *
 */

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 *@ClassName ReferenceDemo
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/11/14 20:14
 *@Version 1.0
 **/
public class ReferenceQueueDemo {
    public static void main(String[] args){
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue= new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o1, referenceQueue);

        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("=========");

        o1 = null;
        System.gc();
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

    }
}
