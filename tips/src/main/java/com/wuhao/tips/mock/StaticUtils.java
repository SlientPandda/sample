package com.wuhao.tips.mock;/**
 *
 */

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *@ClassName StaticUtils
 *@Description TODO
 *@Author wuhao51
 *@Date 2023/1/10 14:56
 *@Version 1.0
 **/
public class StaticUtils {

    public static List<Integer> range(Integer start, Integer end) {
        return IntStream.range(start, end).boxed().collect(Collectors.toList());
    }

    public static String name() {
        return "echo";
    }
}
