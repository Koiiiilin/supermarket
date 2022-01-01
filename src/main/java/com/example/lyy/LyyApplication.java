package com.example.lyy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync//在启动项添加注解
@SpringBootApplication
@MapperScan("com.example.lyy.dao")
public class LyyApplication {

    public static void main(String[] args) {
        SpringApplication.run(LyyApplication.class, args);
    }

}
