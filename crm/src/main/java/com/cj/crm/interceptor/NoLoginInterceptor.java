package com.cj.crm.interceptor;

import com.cj.common.exceptions.NoLoginException;
import com.cj.common.utils.LoginUserUtil;
import com.cj.crm.mapper.UserMapper;
import com.cj.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * Created by Jinmunan
 * 2022/3/21
 * 16:03
 */
public class NoLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    /**
     * 非法请求拦截器
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //通过HttpServletRequest对象获取cookie对象
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        //说明用户没有登录过系统,需要重定向到login.html页面
        if (userId == 0 || null == userService.findById(userId)){
            throw new NoLoginException();
        }
        //请求放行
        return true;
    }
}
