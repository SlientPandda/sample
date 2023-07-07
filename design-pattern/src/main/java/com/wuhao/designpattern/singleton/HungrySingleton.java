package com.wuhao.designpattern.singleton;/**
 *
 */

/**
 *@ClassName HungrySingleInstance
 *@Description 饿汉式单例模式
 *@Author wuhao51
 *@Date 2023/7/7 15:37
 *@Version 1.0
 **/
public class HungrySingleton {
    private static final HungrySingleton instance = new HungrySingleton();


    private HungrySingleton() {

    }

    static public HungrySingleton getInstance() {
        return instance;
    }
}
