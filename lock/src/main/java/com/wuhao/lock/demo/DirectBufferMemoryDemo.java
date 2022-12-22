package com.wuhao.lock.demo;/**
 *
 */

import sun.misc.VM;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 *@ClassName DirectBufferMemoryDemo
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/11/17 10:40
 *@Version 1.0
 **/
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        System.out.println(VM.maxDirectMemory() / (double) 1024 / 1024);

        try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}

        ByteBuffer bb = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
