package com.cj.crm.interceptor;

import com.cj.crm.common.exceptions.NoLoginException;
import com.cj.crm.common.utils.LoginUserUtil;
import com.cj.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * Created by Jinmunan
 * 2022/3/21
 * 16:03
 */


/**
 * 拦截用户是否是登录状态
 * 在目标方法（目标资源）执行前，执行的方法
 * <p>
 * 方法返回布尔类型：
 * 如果返回true，表示目标方法可以被执行
 * 如果返回false，表示阻止目标方法执行
 * <p>
 * 如果判断用户是否是登录状态：
 * 1. 判断cookie中是否存在用户信息（获取用户ID）
 * 2. 数据库中是否存在指定用户ID的值
 * <p>
 * 如果用户是登录状态，则允许目标方法执行；如果用户是非登录状态，则抛出未登录异常 （在全局异常中做判断，如果是未登录异常，则跳转到登录页面）
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
        if (userId == 0 || null == userService.selectByPrimaryKey(userId)) {
            throw new NoLoginException();
        }
        //请求放行
        return true;
    }
}
