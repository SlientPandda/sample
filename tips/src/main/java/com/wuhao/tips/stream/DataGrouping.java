package com.wuhao.tips.stream;/**
 *
 */

import com.wuhao.tips.pojo.User;

import java.util.*;
import java.util.stream.Collectors;

/**
 *@ClassName DataGrouping
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/12/22 10:50
 *@Version 1.0
 **/
public class DataGrouping {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>(Arrays.asList(
                new User("zhangsan", 10, 1),
                new User("lisa", 30, 0),
                new User("abby", 24, 0),
                new User("bob", 60, 1),
                new User("cooper", 40, 0),
                new User("truman", 80, 1)
        ));
        //根据性别进行分组
        //0-女 1-男
        //通过stream流进行分组
        Map<Integer, List<User>> userGenderGroup = userList.stream().collect(Collectors.groupingBy(User::getGender));

        //根据年龄范围进行分组 0-30 30-60 60-100
        Map<String, List<User>> userAgeGroup = userList.stream().collect(Collectors.groupingBy(user ->
                {
                    int age = user.getAge();
                    if (age > 0 && age <= 30) {
                        return "0-30";
                    } else if (age > 30 && age <= 60) {
                        return "30-60";
                    } else {
                        return "60-100";
                    }
                }
        ));

        //统计每个性别的人数
        Map<Integer, Long> userGenderCountGroup = userList.stream().collect(Collectors.groupingBy(User::getGender, Collectors.counting()));

    }
}
