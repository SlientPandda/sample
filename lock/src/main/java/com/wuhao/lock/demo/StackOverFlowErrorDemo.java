package com.wuhao.lock.demo;/**
 *
 */

/**
 *@ClassName StackOverFlowError
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/11/15 14:32
 *@Version 1.0
 **/
public class StackOverFlowErrorDemo {
    public static void main(String[] agrs) {
        stackOverFlowErrorMethod();
    }

    private static void stackOverFlowErrorMethod() {
        stackOverFlowErrorMethod();
    }
}
