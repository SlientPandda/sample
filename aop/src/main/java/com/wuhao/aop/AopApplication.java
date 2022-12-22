package com.wuhao.aop;

import com.wuhao.aop.dto.OperateLogDto;
import com.wuhao.aop.dto.SaveOrderDto;
import com.wuhao.aop.dto.UpdateOrderDto;
import com.wuhao.aop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class AopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);

    }
}
