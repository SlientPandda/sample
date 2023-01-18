package com.wuhao.lock.demo;/**
 *
 */

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *@ClassName VolatileDemo
 *@Description TODO
 *@Author wuhao51
 *@Date 2023/1/16 17:58
 *@Version 1.0
 **/
@Slf4j
public class VolatileDemo {

    static Set<String> set = new HashSet<>();

    public static void main(String[] args) {
        for (int i = 1; i <= 30; i++) {
            int finalI = i;
            new Thread(() -> {
                set.add(String.valueOf(finalI));
            }, String.valueOf(i)).start();
        }
        for (int i = 1; i <= 30; i++) {
            int finalI = i;
            new Thread(() -> {
                set.remove(String.valueOf(finalI));
            }, String.valueOf(i)).start();
        }
        System.out.println(set.toString());
    }
}
