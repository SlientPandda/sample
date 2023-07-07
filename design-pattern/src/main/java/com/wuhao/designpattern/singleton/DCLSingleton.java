package com.wuhao.designpattern.singleton;/**
 *
 */

/**
 *@ClassName DCLSingleton
 *@Description 双重检查单例模式
 *@Author wuhao51
 *@Date 2023/7/7 15:42
 *@Version 1.0
 **/
public class DCLSingleton {
    /**
     *       1、给instance加上volatile关键字，可以禁止指令重排，
     *      由于new DCLSingleton不是原子性的，不加volatile关键字有可能会在多线程条件下，
     *      另一线程获取到未初始化的对象，但已不为空，所以直接返回该为未初始化的对象，造成问题。
     */
    private static volatile DCLSingleton instance = null;

    private DCLSingleton() {

    }

    public static DCLSingleton getInstance() {
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                if (instance == null) {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }
}
