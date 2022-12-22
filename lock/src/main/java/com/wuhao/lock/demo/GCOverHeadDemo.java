package com.wuhao.lock.demo;/**
 *
 */

import java.util.ArrayList;
import java.util.List;

/**
 *@ClassName GCOverHeadDemo
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/11/17 9:48
 *@Version 1.0
 * JVM参数配置演示
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * GC回收时间过长时会抛出oom error
 **/
public class GCOverHeadDemo {
    public static void main(String[] args) {
        int i =0;
        List<String> list = new ArrayList<>();

        try {
            while (true){
                list.add(String.valueOf(++i).intern());
            }
        } catch (Throwable e) {
            System.out.println("================i:"+i);
            e.printStackTrace();
            throw e;
        }
    }
}
