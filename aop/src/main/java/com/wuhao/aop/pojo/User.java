package com.wuhao.aop.pojo;/**
 *
 */

import com.wuhao.aop.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *@ClassName User
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/12/22 11:24
 *@Version 1.0
 **/
@Data
@AllArgsConstructor
public class User {
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "年龄")
    private Integer age;
    @Excel(name = "性别", dictValue = "0=男,1=女")
    private Integer gender;
}
