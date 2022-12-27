package com.wuhao.tips.enumTips.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    STATUS_Y("Y", "OPEN"),
    STATUS_N("N", "CLOSE");

    private String code;
    private String desc;
}