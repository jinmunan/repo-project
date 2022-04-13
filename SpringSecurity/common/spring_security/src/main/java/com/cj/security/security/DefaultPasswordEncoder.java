package com.cj.security.security;

/**
 * Created by Jinmunan
 * 2022/4/12
 * 15:59
 */

import com.cj.utils.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 密码加密处理
 * PasswordEncoder是springsecurity默认的接口,可以不写这个类使用默认的
 * 如果想要拓展就实现这个类
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    public DefaultPasswordEncoder() {
        this(-1);
    }

    public DefaultPasswordEncoder(Integer id) {

    }

    /**
     * 密码加密
     * 使用md5加密
     *
     * @param rawPassword
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5.encrypt(rawPassword.toString());
    }

    /**
     * 密码比对
     * @param rawPassword 加密的密码
     * @param encodedPassword 传入的密码
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5.encrypt(rawPassword.toString()));
    }
}
