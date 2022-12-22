package com.wuhao.lock.demo;/**
 *
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *@ClassName CyclicBarrierDemo
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/11/1 19:14
 *@Version 1.0
 **/
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("summon dragon!");
        });
        for (int i = 1; i <= 7; i++) {
            final int tmpInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 收集到第" + tmpInt + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();

        }
    }
}
