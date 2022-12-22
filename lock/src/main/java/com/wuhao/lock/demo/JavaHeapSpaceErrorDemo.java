package com.wuhao.lock.demo;/**
 *
 */

/**
 *@ClassName JavaHeapSpaceErrorDemo
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/11/15 14:42
 *@Version 1.0
 **/
public class JavaHeapSpaceErrorDemo {
    public static void main(String[] args) {
        String s = new String("12312414124");
        System.out.println(s == s.intern());

    }
}
