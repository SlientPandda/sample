package com.wuhao.thread;

import com.wuhao.thread.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.locks.LockSupport;


@SpringBootApplication
public class ThreadApplication {

    public static void main(String[] args) {

        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.get();
        SpringApplication.run(ThreadApplication.class, args);
    }

}
