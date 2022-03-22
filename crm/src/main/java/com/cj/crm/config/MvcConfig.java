package com.cj.crm.config;

import com.cj.crm.interceptor.NoLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jinmunan
 * 2022/3/21
 * 16:19
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public NoLoginInterceptor noLoginInterceptor() {
        return new NoLoginInterceptor();
    }

    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /*白名单*/
        List<String> list = new ArrayList<String>();
        list.add("/index");
        list.add("/user/login");
        list.add("/css/**");
        list.add("/images/**");
        list.add("/js/**");
        list.add("/lib/**");


        /*拦截器注册*/
        registry.addInterceptor(noLoginInterceptor())
                .addPathPatterns("/**")//要拦截的url
                .excludePathPatterns(list);//添加白名单


        WebMvcConfigurer.super.addInterceptors(registry);
    }
}