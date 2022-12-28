package com.wuhao.tips;

import com.wuhao.tips.pojo.AccessUser;
import com.wuhao.tips.pojo.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class TipsApplicationTests {

    @Test
    void contextLoads() {
        AccessUser accessUser = new AccessUser();
        accessUser.setId("1");
        accessUser.setUserList(Arrays.asList());
        String result = Optional.ofNullable(accessUser).map(AccessUser::getUserList)..ifPresent().map(users -> getName(users.get(0).getName())).orElse("error");
        System.out.println(result);
    }

    private String getName(String name) {
        return name + "123";
    }

}
