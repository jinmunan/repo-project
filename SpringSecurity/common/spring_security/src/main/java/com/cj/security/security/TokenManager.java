package com.cj.security.security;

import io.jsonwebtoken.CompressionCodec;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import sun.security.krb5.internal.crypto.HmacSha1Aes256CksumType;

import java.util.Date;

/**
 * Created by Jinmunan
 * 2022/4/12
 * 16:08
 * 退出处理器
 */
@Component
public class TokenManager {

    //token有效时长
    private long tokenExpiration = 24 * 60 * 60 * 1000;
    //编码密钥
    private String tokenSignKey = "123456";

    /**
     * 根据用户名生成token
     * @param username
     * @return
     */
    public String createToken(String username) {
        String token = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.ES512, tokenSignKey).compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    public String getUserInfoFromToken(String token) {
        String userInfo = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).
                getBody().getSubject();
        return userInfo;
    }

    /**
     * 删除token,可以不删除,设置不携带token就行
     * @param token
     */
    public void removeToken(String token){
    }
}
