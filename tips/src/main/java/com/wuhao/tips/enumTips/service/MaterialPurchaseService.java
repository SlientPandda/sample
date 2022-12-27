package com.wuhao.tips.enumTips.service;

import com.wuhao.tips.enumTips.param.CreateOrderParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MaterialPurchaseService implements IPurchaseService{
    @Override
    public void createOrder(CreateOrderParam param) {
        log.info("MaterialPurchaseService...");
    }
}