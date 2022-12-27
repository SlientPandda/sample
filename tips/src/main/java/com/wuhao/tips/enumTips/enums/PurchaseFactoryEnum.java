package com.wuhao.tips.enumTips.enums;

import com.wuhao.tips.enumTips.service.FinishedPurchaseService;
import com.wuhao.tips.enumTips.service.IPurchaseService;
import com.wuhao.tips.enumTips.service.MaterialPurchaseService;
import com.wuhao.tips.enumTips.utils.SpringContextUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public enum PurchaseFactoryEnum {
    FINISHED("finished","成品采购单", FinishedPurchaseService.class),
    MATERIAL("material","物料采购单", MaterialPurchaseService.class),
    ;

    private String type;
    private String desc;
    private Class<?> service;

    public static IPurchaseService getService(String type){
        for (PurchaseFactoryEnum purchaseFactoryEnum : PurchaseFactoryEnum.values()) {
            if(Objects.equals(purchaseFactoryEnum.getType(),type)){
                return (IPurchaseService) SpringContextUtils.getBean(purchaseFactoryEnum.getService());
            }
        }
        throw new RuntimeException();
    }
}