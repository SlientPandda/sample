package com.wuhao.designpattern.singleton;/**
 *
 */

/**
 *@ClassName LazySingeInstance
 *@Description 懒汉式单例模式
 *@Author wuhao51
 *@Date 2023/7/7 15:37
 *@Version 1.0
 **/
public class LazySingleton {
    private static LazySingleton instance = null;

    private LazySingleton() {

    }

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

}
