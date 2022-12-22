package com.wuhao.lock.demo;/**
 *
 */

/**
 *@ClassName StrongReferenceDemo
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/11/10 18:03
 *@Version 1.0
 **/
public class StrongReferenceDemo {
    public static void main(String[] args){
        Object obj1 = new Object();
        Object obj2 = obj1;
        obj1 = null;
        System.gc();
        System.out.println(obj2);
        System.out.println(obj1);
    }
}
