package com.wuhao.designpattern.mixedstrategy.service.impl;

import com.wuhao.designpattern.mixedstrategy.pojo.TransferFeeRequest;
import com.wuhao.designpattern.mixedstrategy.service.LogisticsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service("4")
public class YTTransfercompany implements LogisticsService {

    private BigDecimal pickFee = BigDecimal.TEN;

    private BigDecimal minDistance = BigDecimal.valueOf(20);


    @Override
    public BigDecimal calculateFee(TransferFeeRequest transferFeeRequest) {
        BigDecimal distance = minDistance.compareTo(transferFeeRequest.getDistance()) > 0 ?
                minDistance : transferFeeRequest.getDistance();
        BigDecimal fee = distance.multiply(transferFeeRequest.getUnitPrice()).add(pickFee);
        // do business
        return fee;
    }

    @Override
    public boolean isCurrentLogistics(Integer type) {
        return Objects.equals(type, 4);
    }
}