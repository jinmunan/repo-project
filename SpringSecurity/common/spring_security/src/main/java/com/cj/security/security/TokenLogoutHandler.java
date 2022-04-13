package com.cj.security.security;

import com.cj.utils.utils.R;
import com.cj.utils.utils.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jinmunan
 * 2022/4/12
 * 16:25
 * 退出处理器
 * 1.删除header的token
 * 2.在redis中删除token信息
 */
public class TokenLogoutHandler implements LogoutHandler {

    private TokenManager tokenManager;

    private RedisTemplate redisTemplate;

    /**
     * 构造器
     * @param tokenManager
     * @param redisTemplate
     */
    public TokenLogoutHandler(TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.tokenManager = tokenManager;
    }

    /**
     * 登出
     * @param request
     * @param response
     * @param authentication
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        //从header获取token
        String token = request.getHeader("token");
        //token不为空,移出token,从redis删除token
        if (null != token) {
            //移出或者不设置
            tokenManager.removeToken(token);
            //从token中获取参数
            String username = tokenManager.getUserInfoFromToken(token);
            redisTemplate.delete(username);
        }
        ResponseUtil.out(response, R.ok());

    }
}
