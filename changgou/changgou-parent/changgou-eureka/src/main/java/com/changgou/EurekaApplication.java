package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by Jinmunan
 * 2022/1/11
 * 9:07
 */

@SpringBootApplication
@EnableEurekaServer //开启Eureka服务
public class EurekaApplication {
    /**
     * 加载启动类
     */
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class);
    }
}
