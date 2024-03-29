package com.wuhao.thread.customthreadpool;/**
 *
 */

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 *@ClassName CustomThreadPoolDemo
 *@Description 自己写一个线程池
 *@Author wuhao51
 *@Date 2023/11/29 10:20
 *@Version 1.0
 **/
@Slf4j
public class CustomThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(1,
                3000, TimeUnit.MILLISECONDS, 1, (queue, task) -> {
            // 1. 死等
//            queue.put(task);
            // 2) 带超时等待
//            queue.offer(task, 1500, TimeUnit.MILLISECONDS);
            // 3) 让调用者放弃任务执行
//            log.debug("放弃{}", task);
            // 4) 让调用者抛出异常
//            throw new RuntimeException("任务执行失败 " + task);
            // 5) 让调用者自己执行任务
            log.info("当前拒绝策略: 让调用者自己执行任务,没有开新线程,直接调用的run()");
            task.run();
        });

        for (int i = 0; i < 4; i++) {
            int j = i;
            threadPool.execute(() -> {
                try {
                    log.info("我先睡1s");
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("我是第 {} 个任务,我马上执行完了", j);
            });
        }
    }
}


