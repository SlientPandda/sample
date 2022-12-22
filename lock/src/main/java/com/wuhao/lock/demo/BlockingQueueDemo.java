package com.wuhao.lock.demo;/**
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *@ClassName BlockingQueueDemo
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/11/1 20:01
 *@Version 1.0
 **/
public class BlockingQueueDemo {
    public static void main(String[] args) throws Exception {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("x");
        blockingQueue.put("x");




    }
}
