package com.wuhao.tips.stream;/**
 *
 */

import com.wuhao.tips.pojo.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *@ClassName DataFiltering
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/12/22 16:00
 *@Version 1.0
 **/
public class DataFiltering {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>(Arrays.asList(
                new User("zhangsan", 10, 1),
                new User("lisa", 30, 0),
                new User("abby", 24, 0),
                new User("bob", 60, 1),
                new User("cooper", 40, 0),
                new User("truman", 80, 1)
        ));
//        按条件过滤，取出所有年龄25以下的用户
        List<User> userList1 = userList.stream().filter(user -> user.getAge() < 25).collect(Collectors.toList());


//        保留前几项、跳过前几项
        List<User> userList2 = userList.stream().skip(5).limit(5).collect(Collectors.toList());


//        寻找符合条件的第一项
        Optional<User> user1 = userList.stream().filter(user -> user.getName().startsWith("a")).findFirst();

//        判断是否有符合的数据、是否都符合、是否都不符合
        boolean b = userList.stream().allMatch(user -> user.getAge() < 30);
        boolean b1 = userList.stream().anyMatch(user -> user.getAge() < 30);
        boolean b2 = userList.stream().noneMatch(user -> user.getAge() < 30);

//        保留第一个不合法数据前面的所有数据和第一项不符合条件后面的数据 takeWhile和dropWhile 是jdk9引入的
//        userList.stream().takeWhile(u -> u.getAge < 30).collect(Collectors.toList());
//
//        userList.stream().dropWhile(u -> u.getAge < 30).collect(Collectors.toList());


    }

}
