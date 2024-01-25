package com.wuhao.tips.optional;/**
 *
 */

import com.wuhao.tips.pojo.AccessUser;
import com.wuhao.tips.pojo.User;

import java.util.ArrayList;
import java.util.List;
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
//        optional链式操作，需要使用map，如果在map中多层get方法调用的话，也是会报空指针异常的，而多层map就不会。
        AccessUser accessUser = new AccessUser("1", new ArrayList<>(), new User("zhangsan", 20, 1));
        Integer age = Optional.ofNullable(accessUser).map(AccessUser::getUser).map(User::getAge).orElse(30);


//        optional短路
//        只要调用了orElse方法不管前面的容器是否有值，参数里的方法都会执行，所以应该采用orElseGet使用lambda表达式的方式不会执行参数中的表达式
        optionalUser.orElseGet(() -> new User("lisi", 30, 1)).getAge();
        //    optional抛出异常
        optionalUser.orElseThrow(() -> new RuntimeException("用户为空！"));

        optionalUser.map(User::getAge).filter(a -> a < 30).ifPresent(System.out::println);
        //判断集合，需要先判断集合长度，否则直接get会越界
        List<String> list = new ArrayList<>();
        byte[] bytes = Optional.ofNullable(list)
                .filter(item -> !item.isEmpty())
                .map(item -> item.get(0))
                .map(String::getBytes)
                .orElse(null);

    }
}
