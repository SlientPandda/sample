package com.wuhao.lock.demo;/**
 *
 */

import java.util.concurrent.TimeUnit;

/**
 *@ClassName HelloGc
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/11/9 16:26
 *@Version 1.0
 **/
public class HelloGC {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
//        long totalMemory = Runtime.getRuntime().totalMemory();
//        long maxMemory = Runtime.getRuntime().maxMemory();
//        System.out.println(totalMemory / (double) 1024 / 1024 + "" + maxMemory / (double) 1024 / 1024);
        //查看gc详细日志命令
        byte[] byteArray = new byte[50 * _1MB];

    }
}
