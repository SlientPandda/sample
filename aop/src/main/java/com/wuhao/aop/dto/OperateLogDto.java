package com.wuhao.aop.dto;/**
 *
 */

import lombok.Data;

/**
 *@ClassName OperateLogDto
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/12/9 19:40
 *@Version 1.0
 **/
@Data
public class OperateLogDto {
    private Long orderId;
    private String desc;
    private String result;
}
