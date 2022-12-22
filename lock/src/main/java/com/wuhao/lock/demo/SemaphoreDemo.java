package com.wuhao.lock.demo;/**
 *
 */

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *@ClassName SemaphoreDemo
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/11/1 19:47
 *@Version 1.0
 *
 * 多个线程抢多个共享变量而不是通过加锁多个线程只能有一个线程获取锁
 * 获取后必须释放
 **/
public class SemaphoreDemo {

    public static void main(String[] args) {
        //模拟停车位
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t抢到车位");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t停车3秒后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }

}
