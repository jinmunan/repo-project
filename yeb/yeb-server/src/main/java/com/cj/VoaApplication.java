package com.cj;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@SpringBootApplication
@MapperScan("com.cj.server.mapper")
public class VoaApplication {
    public static void main(String[] args) {
        SpringApplication.run(VoaApplication.class, args);
    }
}