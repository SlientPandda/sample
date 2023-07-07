package com.wuhao.thread.problem;/**
 *
 */

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *@ClassName AlternatePrint
 *@Description 多个线程按A-B-C顺序交替打印，总共打印100次
 *@Author wuhao51
 *@Date 2023/7/7 14:59
 *@Version 1.0
 **/
public class AlternatePrint {

    public static void main(String[] args) {
        Semaphore a = new Semaphore(1);
        Semaphore b = new Semaphore(0);
        Semaphore c = new Semaphore(0);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        new Thread(() -> {
            try {
                while (true && atomicInteger.getAndIncrement() < 100) {
                    a.acquire();
                    System.out.println("A");
                    b.release();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        new Thread(() -> {
            try {
                while (true && atomicInteger.getAndIncrement() < 100) {
                    b.acquire();
                    System.out.println("B");
                    c.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                while (true && atomicInteger.getAndIncrement() < 100) {
                    c.acquire();
                    System.out.println("C");
                    a.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
