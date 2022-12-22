package com.wuhao.lock.demo;/**
 *
 */

import java.util.concurrent.TimeUnit;

/**
 *@ClassName UnableCreateNewThreadDemo
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/11/17 11:48
 *@Version 1.0
 **/
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        for (int i = 1; ; i++) {
            System.out.println("**** i=" + i);

            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();

        }
    }
}
