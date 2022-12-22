package com.wuhao.lock.demo;/**
 *
 */

import com.sun.org.apache.bcel.internal.generic.FADD;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *@ClassName ProdConsumer_BlockQueueDemo
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/11/3 20:06
 *@Version 1.0
 **/

class MyResource {
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception {
        String data = null;
        boolean retValue;

        while (FLAG) {
            data = String.valueOf(atomicInteger.incrementAndGet());
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "\t" + "插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t" + "插入队列" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println(Thread.currentThread().getName() + "\t" + "大老板叫停了");
    }

    public void myConsumer() throws Exception {
        String result = null;
        while (FLAG) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(result == null){
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t" + "超过两秒钟没有取到消息，消费退出");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "消费队列" + result + "成功");
        }
    }

    public void stop(){
        FLAG = false;
    }

}


public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "生产线程启动！");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Prod").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "消费线程启动！");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myResource.stop();


    }
}
