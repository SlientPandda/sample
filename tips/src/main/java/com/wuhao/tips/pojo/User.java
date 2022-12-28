package com.wuhao.tips.pojo;/**
 *
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@ClassName User
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/12/22 11:24
 *@Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private Integer age;
    private Integer gender;
}
