package com.wuhao.lock.demo;/**
 *
 */

import java.lang.ref.WeakReference;

/**
 *@ClassName WeakReferenceDemo
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/11/10 20:21
 *@Version 1.0
 **/
public class WeakReferenceDemo {

    public static void main(String[] args){
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1=null;
        System.gc();

        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}
