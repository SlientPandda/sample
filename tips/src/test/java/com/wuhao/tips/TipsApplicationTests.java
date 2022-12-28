package com.wuhao.tips;

import com.wuhao.tips.pojo.AccessUser;
import com.wuhao.tips.pojo.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class TipsApplicationTests {

    @Test
    void contextLoads() {
        AccessUser accessUser = new AccessUser();
        accessUser.setId("1");
        accessUser.setUserList(new ArrayList<>());
        accessUser.setUser(null);

        String result = Optional.ofNullable(accessUser)
                .map(AccessUser::getUserList)
                .filter(users -> users.size() > 0)
                .map(users -> getName(users.get(0).getName()))
                .orElse(null);
        String name = Optional.ofNullable(accessUser)
                .map(AccessUser::getUser)
                .map(User::getName)
                .orElse(null);

        System.out.println(result);
        System.out.println(name);
    }

    private String getName(String name) {
        return name + "123";
    }

}
