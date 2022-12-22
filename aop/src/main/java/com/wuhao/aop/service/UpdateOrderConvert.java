package com.wuhao.aop.service;/**
 *
 */

import com.wuhao.aop.dto.OperateLogDto;
import com.wuhao.aop.dto.UpdateOrderDto;

/**
 *@ClassName UpdateOrderConvert
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/12/14 17:45
 *@Version 1.0
 **/
public class UpdateOrderConvert implements Convert<UpdateOrderDto> {
    @Override
    public OperateLogDto convert(UpdateOrderDto updateOrderDto) {
        OperateLogDto operateLogDto = new OperateLogDto();
        operateLogDto.setOrderId(updateOrderDto.getId());
        return operateLogDto;
    }
}
