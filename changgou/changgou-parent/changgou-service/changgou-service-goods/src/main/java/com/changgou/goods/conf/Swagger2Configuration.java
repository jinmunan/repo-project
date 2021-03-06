package com.changgou.goods.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Jinmunan
 * 2022/1/17
 * 17:18
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {
    @Bean
    public Docket createRestApi() {
        //扫描的包结构
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.changgou"))
                .paths(PathSelectors.any())
                .build();
    }

    //1、标题 2、小标题
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("畅购商城API文档")
                .description("畅购商城API文档-商品管理")
                .version("1.0")
                .build();
    }
}
