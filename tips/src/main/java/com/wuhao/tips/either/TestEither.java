package com.wuhao.tips.either;/**
 *
 */

import com.wuhao.tips.pojo.User;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *@ClassName TestEither
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/12/26 9:26
 *@Version 1.0
 **/
public class TestEither {
    public static void main(String[] args) {
//        stream流中不允许抛出异常，如果对在map里进行trycatch，只能取到第一次报错信息，程序就终止了。
//        List<User> userList = Stream.iterate(1, i -> i + 1).limit(100).map(i1 -> {
//            try {
//                return readLine(i1);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }).collect(Collectors.toList());
//
//
//        for (User user : userList) {
//            System.out.println(user);
//        }


//        改造readLine方法使其返回Either

//        将List<Either>转换为Either
        List<Either<String, User>> userEitherList = Stream.iterate(1, i -> i + 1).limit(100).map(TestEither::readLine).collect(Collectors.toList());
        Either<String, List<User>> either = Either.sequence(userEitherList, (s1, s2) -> s1 + "\n" + s2);

        if (either.isLeft()) {
            System.out.println(either.getLeft());
        } else {
            for (User user : either.getRight()) {
                System.out.println(user);
            }
        }
    }

    private static Either<String, User> readLine(int i) {
        if (new Random().nextInt(100) <= 50) {
            return Either.right(new User("zhangsan", 20, 1));
        } else {
            return Either.left("第" + i + "行有数据错误！");
        }
    }
}
