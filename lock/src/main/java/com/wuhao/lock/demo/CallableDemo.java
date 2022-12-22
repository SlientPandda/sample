package com.wuhao.lock.demo;/**
 *
 */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *@ClassName CallableDemo
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/11/4 12:00
 *@Version 1.0
 **/
class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("******");
        return 1024;
    }

}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        //多个线程计算一个futuretask，只会计算一次
        Thread t1 = new Thread(futureTask, "AA");
        t1.start();
        //get方法会阻塞当前线程，建议放在最后
        System.out.println(futureTask.get());

    }
}
