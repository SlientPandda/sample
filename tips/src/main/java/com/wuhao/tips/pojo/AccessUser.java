package com.wuhao.tips.pojo;/**
 *
 */

import lombok.Data;

import java.util.List;

/**
 *@ClassName AccessUser
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/12/28 19:42
 *@Version 1.0
 **/
@Data
public class AccessUser {
    private String id;
    private List<User> userList;
    private User user;
}
