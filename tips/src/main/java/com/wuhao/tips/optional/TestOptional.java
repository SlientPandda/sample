package com.wuhao.tips.optional;/**
 *
 */

import com.wuhao.tips.pojo.User;

import java.util.Optional;

/**
 *@ClassName TestOptional
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/12/23 9:57
 *@Version 1.0
 **/
public class TestOptional {
    public static void main(String[] args) {
        User zhangsan = new User("zhangsan", 20, 1);
        //将可能为空的对象转换为optional对象
//        of为空会报错，ofnullable不会
        Optional<User> optionalUser = Optional.ofNullable(zhangsan);
//        optional链式操作
        Integer age = optionalUser.map(User::getAge).orElse(20);

//        optional短路
//        只要调用了orElse方法不管前面的容器是否有值，参数里的方法都会执行，所以应该采用orElseGet使用lambda表达式的方式不会执行参数中的表达式
        optionalUser.orElseGet(() -> new User("lisi", 30, 1)).getAge();
        //    optional抛出异常
        optionalUser.orElseThrow(() -> new RuntimeException("用户为空！"));

        optionalUser.map(User::getAge).filter(a -> a < 30).ifPresent(System.out::println);

    }
}
