package com.shirologin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication()
@MapperScan("com.shirologin.dao")
public class ShirologinApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShirologinApplication.class, args);
    }

}