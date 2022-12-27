package com.wuhao.designpattern.mixedstrategy.service;

import com.wuhao.designpattern.mixedstrategy.pojo.TransferFeeRequest;

import java.math.BigDecimal;

public interface LogisticsService {

    boolean isCurrentLogistics(Integer type);

    BigDecimal calculateFee(TransferFeeRequest transferFeeRequest);
}