package com.wuhao.lock.demo;/**
 *
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *@ClassName ProdConsumer_TraditionDemo
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/11/2 19:48
 *@Version 1.0
 *
 * 题目：一个初始值为零的变量，两个线程对其交替操作，一个加1一个减1，来5轮
 * 1、线程 操作（方法） 资源类
 * 2、判断 干活       通知
 * 3、防止虚假唤醒机制
 **/

class ShareData {
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            //应使用while避免虚假唤醒现象
            while (num != 0) {
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception {
        lock.lock();
        try {
            while (num == 0) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    shareData.increment();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    shareData.decrement();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BBB").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    shareData.increment();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "CCC").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    shareData.decrement();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "DDD").start();
    }
}
