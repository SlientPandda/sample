package com.wuhao.tips.mock;/**
 *
 */

import lombok.Data;

/**
 *@ClassName SpyDemo
 *@Description TODO
 *@Author wuhao51
 *@Date 2023/1/10 13:58
 *@Version 1.0
 **/
@Data
public class SpyDemo {
    public int add(int a, int b) {
        return a + b;
    }
}
