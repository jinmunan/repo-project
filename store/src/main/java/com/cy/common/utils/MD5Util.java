package com.cy.common.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.UUID;

public class MD5Util {
    // 散列次数
    private static int hashIterations = 3;


    // 内置salt
    private static String public_salt = "958AEF84DB49419689159022A74D547E";

    /**
     * md5加密工具类
     *
     * @param source 要用共盐加密的字符串
     * @return
     */
    private static String md5_public_salt(String source) {
        return new Md5Hash(source, public_salt, hashIterations).toString();
    }

    /**
     * @param source 原始密码
     * @param salt   私盐
     * @return
     */
    public static String md5_private_salt(String source, String salt) {
        //1.先用共盐对原始密码加密
        //2.再对加密的后密文用私盐加密一次
        return new Md5Hash(md5_public_salt(source), salt, hashIterations).toString();
    }

    /**
     * 定义一个md5加密算法
     */
    public static String getMD5Password(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes(StandardCharsets.UTF_8)).toLowerCase(Locale.ROOT);
        }
        return password;
    }
}