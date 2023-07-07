package com.wuhao.thread.service;/**
 *
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 *@ClassName Thread
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/7/25 15:11
 *@Version 1.0
 **/
@Component
@Slf4j
public class ThreadService {
    //创建线程
    public void start1() {
        Thread t = new Thread(() -> {
            System.out.println("new thread start!");
        });
        t.start();
    }

    //创建线程池，并使用countdownlatch
    public void start2() {
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        //创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                20,
                100,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                log.info("thread{} execute", countDownLatch);
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
            System.out.println("main end!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 关闭线程池
        executor.shutdown();
    }

    @Async("myThreadPool")
    public void start3(){
        log.info(Thread.currentThread().getName() + "start3!");
    }

    @Async("myThreadPool")
    public void start4(){
        log.info(Thread.currentThread().getName() + "start4!");
    }



}
