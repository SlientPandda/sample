package com.wuhao.lock.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 *
 */
@AllArgsConstructor
public enum Country {

    /**
     *
     */
    One(1, "一"), Two(2, "二"), Three(3, "三");

    @Getter
    private Integer retCode;
    @Getter
    private String retMsg;

    public static String retMsg(Integer retcode) {
        for (Country country : Country.values()) {
            if (retcode.equals(country.retCode)) {
                return country.retMsg;
            }
        }
        return null;
    }
}
