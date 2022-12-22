package com.wuhao.lock.demo;/**
 *
 */

import java.util.HashMap;
import java.util.concurrent.*;

/**
 *@ClassName MyThreadPoolDemo
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/11/4 17:53
 *@Version 1.0
 * 第四种获得使用java多线程的方式，线程池
 **/
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());


        //模拟10个用户来处理业务，每个用户就是一个来自外部的请求线程
        try {
            for (int i = 0; i < 20; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t" + "用户办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void doExecutors() {
        // 一池五个处理线程
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //一池一个处理线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //一池N个处理线程
        ExecutorService threadPool = Executors.newCachedThreadPool();

        //模拟10个用户来处理业务，每个用户就是一个来自外部的请求线程
        try {
            for (int i = 0; i < 20; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t" + "用户办理业务");
                });
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
