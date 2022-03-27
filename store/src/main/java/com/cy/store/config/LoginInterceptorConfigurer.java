package com.cy.store.config;

/**
 * Created by Jinmunan
 * 2022/3/19
 * 14:14
 */

import com.cy.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**处理器拦截器的注册*/
@Configuration //加载拦截器
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

    /**
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建自定义拦截器
        LoginInterceptor interceptor = new LoginInterceptor();
        /*白名单*/
        List<String> list = new ArrayList<String>();
        list.add("/bootstrap3/**");
        list.add("/css/**");
        list.add("/images/**");
        list.add("/js/**");
        list.add("/web/register.html");
        list.add("/web/login.html");
        list.add("/web/index.html");
        list.add("/web/product.html");
        list.add("/users/reg");
        list.add("/users/login");
        list.add("/dictDistrict/**");
        list.add("/products/**");

        /*拦截器注册*/
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")//要拦截的url
                .excludePathPatterns(list);//添加白名单


        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
