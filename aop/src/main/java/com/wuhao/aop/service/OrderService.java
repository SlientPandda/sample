package com.wuhao.aop.service;/**
 *
 */

import com.wuhao.aop.annotation.RecordOperate;
import com.wuhao.aop.dto.SaveOrderDto;
import com.wuhao.aop.dto.UpdateOrderDto;
import org.springframework.stereotype.Service;

/**
 *@ClassName OrderService
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/12/8 20:22
 *@Version 1.0
 **/
@Service
public class OrderService {
    /**
     * 想打印的字段名称不同，但是注解只能传枚举、基本类型、class等，可以定义一个接口
     *
     */


    @RecordOperate(desc = "保存订单", convert = SaveOrderConvert.class)
    public Boolean saveOrder(SaveOrderDto saveOrderDto) {
        System.out.println("save order, orderId:" + saveOrderDto.getOrderId());
        return true;
    }

    @RecordOperate(desc = "更新订单", convert = UpdateOrderConvert.class)
    public Boolean updateOrder(UpdateOrderDto updateOrderDto) {
        System.out.println("update order, id:" + updateOrderDto.getId());
        return true;
    }

}
