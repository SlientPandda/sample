package com.wuhao.tips.enumTips.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PurchaseOrder {

    /**
     * 采购单号
     */
    private String orderId;

    /**
     * 是否关闭(Y,N)
     */
    private String status;

    /**
     * 打款日期
     */
    private Long payTime;

    /**
     * 到货时间
     */
    private Long arrivalTime;

    /**
     * 完结日期
     */
    private Long endingTime;

}