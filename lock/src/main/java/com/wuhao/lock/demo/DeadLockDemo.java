package com.wuhao.lock.demo;

import java.util.concurrent.TimeUnit;

/**
 *
 */

class HoldLockThread implements Runnable {
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t持有" + lockA + "\t请求" + lockB);
            try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t请求到" + lockB);
            }
        }
    }
}


/**
 * @ClassName DeadLockDemo
 * @Description TODO
 * @Author wuhao51
 * @Date 2022/11/9 10:52
 * @Version 1.0
 **/
public class DeadLockDemo {

    public static void main(String[] args) {
        HoldLockThread threadA = new HoldLockThread("lockA", "lockB");
        HoldLockThread threadB = new HoldLockThread("lockB", "lockA");
        new Thread(threadA, "ThreadAAA").start();
        new Thread(threadB, "ThreadBBB").start();

        /**
         * linux ps -ef|grep xxxx ls -l
         * windows下的Java运行程序 也有类似的ps查看进程的命令，但是目前我们需要查看的只是java
         * jps = java ps    jps -l 找对应进程编号查
         * 用jstack 进程编号 命令查看是否死锁
         */
    }
}
