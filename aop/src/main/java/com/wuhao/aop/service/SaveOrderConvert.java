package com.wuhao.aop.service;/**
 *
 */

import com.wuhao.aop.dto.OperateLogDto;
import com.wuhao.aop.dto.SaveOrderDto;

/**
 *@ClassName SaveOrderConvert
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/12/14 17:43
 *@Version 1.0
 **/
public class SaveOrderConvert implements  Convert<SaveOrderDto> {
    @Override
    public OperateLogDto convert(SaveOrderDto saveOrderDto) {
        OperateLogDto operateLogDto = new OperateLogDto();
        operateLogDto.setOrderId(saveOrderDto.getOrderId());
        return operateLogDto;
    }
}
