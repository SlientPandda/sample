package com.wuhao.thread.config;/**
 *
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *@ClassName MyThreadPoolConfig
 *@Description TODO
 *@Author wuhao51
 *@Date 2023/7/7 14:16
 *@Version 1.0
 **/
//@EnableAsync
@Configuration
public class MyThreadPoolConfig {

    @Bean
    public ThreadPoolExecutor myThreadPool() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                20,
                100,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new MyThreadFactory("mythreadpool"),
                new ThreadPoolExecutor.AbortPolicy());
//        executor.allowCoreThreadTimeOut();
//        executor.allowsCoreThreadTimeOut();
        return executor;
    }


    static class MyThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        MyThreadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = name +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }


        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
