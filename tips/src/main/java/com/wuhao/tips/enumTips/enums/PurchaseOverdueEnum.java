package com.wuhao.tips.enumTips.enums;

import com.wuhao.tips.enumTips.domain.PurchaseOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum PurchaseOverdueEnum {

    PAY("pay","是否逾期打款",order -> System.currentTimeMillis() > order.getPayTime()),
    ARRIVAL("arrival","是否逾期到货",order -> System.currentTimeMillis() > order.getArrivalTime()),
    ENDING("ending","是否逾期完结",order -> System.currentTimeMillis() > order.getEndingTime()),
    ;

    private String code;
    private String desc;
    private final PurchaseOverdueFunction function;

    public static Boolean isOverdue(String code, PurchaseOrder purchaseOrder){
        for (PurchaseOverdueEnum value : PurchaseOverdueEnum.values()) {
            if(Objects.equals(value.getCode(),code)){
                return value.getFunction().proceed(purchaseOrder);
            }
        }
        throw new RuntimeException();
    }


    @FunctionalInterface
    public interface PurchaseOverdueFunction{
        Boolean proceed(PurchaseOrder purchaseOrder);
    }
}