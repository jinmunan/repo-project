package com.cj.security.config;

import com.cj.security.filter.TokenAuthFilter;
import com.cj.security.filter.TokenLoginFilter;
import com.cj.security.security.DefaultPasswordEncoder;
import com.cj.security.security.TokenLogoutHandler;
import com.cj.security.security.TokenManager;
import com.cj.security.security.UnAuthEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * Created by Jinmunan
 * 2022/4/12
 * 15:58
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启注解
@SuppressWarnings("all")
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private TokenManager tokenManager;
    private DefaultPasswordEncoder defaultPasswordEncoder;
    private RedisTemplate redisTemplate;

    @Autowired
    public TokenWebSecurityConfig(UserDetailsService userDetailsService,
                                  TokenManager tokenManager,
                                  DefaultPasswordEncoder defaultPasswordEncoder,
                                  RedisTemplate redisTemplate) {
        this.userDetailsService = userDetailsService;
        this.tokenManager = tokenManager;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 配置设置 -> 设置退出的地址和token,redis操作地址
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new UnAuthEntryPoint()) //没有权限访问的时候执行
                .and().csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().logout().logoutUrl("/admin/acl/index/logout")//设置退出的路径
                .addLogoutHandler(new TokenLogoutHandler(tokenManager, redisTemplate))//添加注销处理程序 自定义的
                .and()
                .addFilter(new TokenLoginFilter(tokenManager, redisTemplate, authenticationManager())) //自定义认证过滤器
                .addFilter(new TokenAuthFilter(authenticationManager(), tokenManager, redisTemplate)) //自定义授权过滤器
                .httpBasic();
    }

    /**
     * 调用userDetailsService密码处理
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }

    /**
     * 不进行认证的路径
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**");
    }
}
