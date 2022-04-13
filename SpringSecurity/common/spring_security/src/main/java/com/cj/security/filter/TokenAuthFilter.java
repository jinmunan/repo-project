package com.cj.security.filter;

import com.cj.security.security.TokenManager;
import com.cj.utils.utils.R;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Jinmunan
 * 2022/4/12
 * 15:59
 * 授权
 */
public class TokenAuthFilter extends BasicAuthenticationFilter {
    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    /**
     * 构造器 创建对象用的
     *
     * @param authenticationManager
     * @param tokenManager
     * @param redisTemplate
     */
    public TokenAuthFilter(AuthenticationManager authenticationManager, TokenManager tokenManager, RedisTemplate redisTemplate) {
        super(authenticationManager);
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 获取"用户名密码身份验证令牌"对象并放置在权限上下文中
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //获取当前认证成功用户信息
        UsernamePasswordAuthenticationToken authRequest = getAuthentication(request);
        //判断 有权限信息就放到权限上下文中
        if (authRequest != null) {
            SecurityContextHolder.getContext().setAuthentication(authRequest);
        }
    }


    /**
     * 从header请求头里面拿到token  ->  从token中拿到用户名   ->   从redis中获取权限列表   -> 返回"用户名密码身份验证令牌"对象
     *
     * @param request
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        //从header或取token
        String token = request.getHeader("token");
        if (token != null) {
            //从token中获取用户名
            String username = tokenManager.getUserInfoFromToken(token);

            //从redis获取用户权限列表
            List<String> permissionValueList = (List<String>) redisTemplate.opsForValue().get(username);
            Collection<GrantedAuthority> authority = new ArrayList<>();
            for (String permissionValue : permissionValueList) {
                SimpleGrantedAuthority auth = new SimpleGrantedAuthority(permissionValue);
                authority.add(auth);
            }
            return new UsernamePasswordAuthenticationToken(username, token, authority);
        }
        return null;
    }
}
