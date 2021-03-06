package com.stone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.stone.mapper")
public class ChinaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChinaServiceApplication.class, args);
    }

}
