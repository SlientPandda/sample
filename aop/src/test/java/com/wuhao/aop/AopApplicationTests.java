package com.wuhao.aop;

import com.wuhao.aop.dto.SaveOrderDto;
import com.wuhao.aop.dto.UpdateOrderDto;
import com.wuhao.aop.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AopApplicationTests {
    @Autowired
    OrderService orderService;

    @Test
    void contextLoads() {
        orderService.saveOrder(new SaveOrderDto(1L));
        orderService.updateOrder(new UpdateOrderDto(2L));
    }

}
