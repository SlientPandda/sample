package com.wuhao.aop.service;/**
 *
 */

import com.wuhao.aop.dto.SaveOrderDto;
import com.wuhao.aop.dto.UpdateOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *@ClassName InitService
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/12/14 18:22
 *@Version 1.0
 **/
@Component
public class InitService implements CommandLineRunner {

    @Autowired
    OrderService orderService;

    @Override
    public void run(String... args) throws Exception {
        orderService.saveOrder(new SaveOrderDto(1L));
        orderService.updateOrder(new UpdateOrderDto(2L));
    }
}
