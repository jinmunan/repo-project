package com.cj.security.security;

import com.cj.utils.utils.R;
import com.cj.utils.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jinmunan
 * 2022/4/12
 * 16:55
 * 未授权统一处理类
 */
public class UnAuthEntryPoint implements AuthenticationEntryPoint {
    /**
     * 未登录统一处理器
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtil.out(response,R.error());
    }
}
