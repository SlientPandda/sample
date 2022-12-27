package com.wuhao.designpattern.mixedstrategy.controller;

import com.wuhao.designpattern.mixedstrategy.pojo.TransferFeeRequest;
import com.wuhao.designpattern.mixedstrategy.service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;


/**
 * 混合设计模式的实现
 * 使用工厂模式来生成策略，避免策略类暴露给调用者，现在调用者只需要一个约束条件即可获得需要的策略
 */
@RestController
public class LogisticsController {

//    @Autowired
//    private LogisticsService logisticsService;

    //将所有的实现类注入
//    @Autowired
//    private List<LogisticsService> logisticsService;

    //也可以使用map注入的方式（需要在类上的Service注解中写上value值）
    @Autowired
    private Map<String, LogisticsService> logisticsServiceMap;


    @PostMapping("/calculate")
    private BigDecimal calculateFee(@RequestBody TransferFeeRequest transferFeeRequest) {

//        LogisticsService logisticsService = this.logisticsService.stream().filter(l -> l.isCurrentLogistics(transferFeeRequest.getType()))
//                .findFirst().orElse(null);
        LogisticsService logisticsService = logisticsServiceMap.getOrDefault(String.valueOf(transferFeeRequest.getType()), null);
        if (logisticsService != null) {
            return logisticsService.calculateFee(transferFeeRequest);
        }
        return null;
    }
}