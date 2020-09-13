package com.wyt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.wyt.dao")
public class ZhiyunApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhiyunApplication.class, args);
    }

}
