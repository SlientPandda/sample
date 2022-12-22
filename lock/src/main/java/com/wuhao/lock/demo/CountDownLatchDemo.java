package com.wuhao.lock.demo;/**
 *
 */

import java.util.concurrent.CountDownLatch;

/**
 *@ClassName CountDownLatchDemo
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/11/1 15:51
 *@Version 1.0
 **/
public class CountDownLatchDemo {

    public static void main(String[] args) throws Exception {
        closeDoor();
    }

    private static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t离开教室");
                countDownLatch.countDown();
            }, Country.retMsg(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t关门走人");

    }


}
