package com.cj.fmsc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.cj.fmsc.mapper")
@SpringBootApplication
public class FmscApplication {

    public static void main(String[] args) {
        SpringApplication.run(FmscApplication.class, args);
    }

}
