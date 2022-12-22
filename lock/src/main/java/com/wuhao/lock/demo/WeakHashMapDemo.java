package com.wuhao.lock.demo;/**
 *
 */

import java.util.WeakHashMap;

/**
 *@ClassName WeakHashMapDemo
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/11/11 11:43
 *@Version 1.0
 **/
public class WeakHashMapDemo {
    public static void main(String[] args) {
        myWeakHashMap();
        while (true);
    }

    private static void myWeakHashMap() {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key =  new Integer(1);
        String value = "WeakHashMap";
        Integer key2 = 2;
        String value2 = "WeakHashMap2";

        map.put(key, value);

        key = null;
        System.out.println(map);

        //gc后 weakhashmap被回收
        System.gc();
        System.out.println(map + "\t" + map.size());
    }
}
