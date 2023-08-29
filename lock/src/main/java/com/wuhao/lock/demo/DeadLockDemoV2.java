package com.wuhao.lock.demo;/**
 *
 */

import java.util.concurrent.TimeUnit;

/**
 *@ClassName DeadLockDemo
 *@Description TODO
 *@Author wuhao51
 *@Date 2023/8/2 13:57
 *@Version 1.0
 **/
public class DeadLockDemoV2 {

    public static void main(String[] args){
        Integer a = 1;
        Integer b = 2;

        new Thread(()->{
            synchronized (a) {
                System.out.println("thread 1 have a");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println("thread 1 have b");
                }
            }
        }).start();

        new Thread(()->{
            synchronized (b){
                System.out.println("thread 2 have b");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a){
                    System.out.println("thread 2 have a");
                }
            }
        }).start();

    }


}



