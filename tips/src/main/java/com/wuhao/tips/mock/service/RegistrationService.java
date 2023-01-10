package com.wuhao.tips.mock.service;


import com.wuhao.tips.mock.entity.User;

public interface RegistrationService {
    User register(String name, String phone) throws Exception;
}
