package com.wuhao.thread.init;/**
 *
 */

import com.wuhao.thread.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *@ClassName StartRunner
 *@Description TODO
 *@Author wuhao51
 *@Date 2022/7/26 16:25
 *@Version 1.0
 **/
@Component
public class StartRunner implements CommandLineRunner {
    @Autowired
    ThreadService threadService;
    @Override
    public void run(String... args){
        threadService.start2();
    }
}
