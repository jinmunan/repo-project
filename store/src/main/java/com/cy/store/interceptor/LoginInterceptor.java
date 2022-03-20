package com.cy.store.interceptor;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器,避免用户无登录操作
 * Created by Jinmunan
 * 2022/3/19
 * 13:59
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 在所有请求前完成
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理器 url 和controller 映射
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //通过HttpServletRequest对象获取session对象
        Object uid = request.getSession().getAttribute("uid");
        log.debug("从session获取的uid为:"+uid);
        if (uid == null) {
            //说明用户没有登录过系统,需要重定向到login.html页面
            response.sendRedirect("/web/login.html");
            //结束后续的调用
            return false;
        }
        //请求放行
        return true;
    }
}
