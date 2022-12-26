package com.wuhao.tips.stream;/**
 *
 */

import com.wuhao.tips.pojo.User;

import java.util.*;
import java.util.stream.Stream;

/**
 *@ClassName DataSorting
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/12/22 11:55
 *@Version 1.0
 **/
public class DataSorting {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>(Arrays.asList(
                new User("zhangsan", 10, 1),
                new User("lisa", 30, 0),
                new User("abby", 24, 0),
                new User("bob", 60, 1),
                new User("cooper", 40, 0),
                new User("truman", 80, 1)
        ));
        //1、java怎么支持数据排序？Comparable和Comparator接口，基本类型的包装类已经实现comparable接口，可以直接进行比较
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Collections.sort(list);

        //2、如果是我们自己创建的pojo，需要构造Comparator
        //针对一些内置类，Java已经提供一些方法可以用于构造
        Comparator<Integer> comparator = Integer::compare;

        //3、按字段比较 按用户年龄排序
        Comparator<User> userComparator1 = Comparator.comparing(User::getAge);
        userList.sort(userComparator1);


        //4、按字段比较，先按年龄排序，再按性别排序
        Comparator<User> userComparator2 = Comparator.comparing(User::getAge).thenComparing(User::getGender);
        userList.sort(userComparator2);


        //5、倒序，按年龄排序
        Comparator<User> userComparator3 = Comparator.comparing(User::getAge).reversed();
        userList.sort(userComparator3);

        //6、排序api
        //数组存在数组里
        User[] array = userList.stream().toArray(User[]::new);
        Arrays.sort(array, userComparator1);

//        数据存在list集合里
        Collections.sort(userList, userComparator1);

//        不改变原始的集合进行排序
        Stream.of(array).sorted(userComparator1);
        userList.stream().sorted(userComparator1);
    }
}
