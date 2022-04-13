package com.cj.security.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Created by Jinmunan
 * 2022/4/12
 * 15:58
 */
@Data
@ApiModel(description = "用户实体类")
public class User {
    private String Username;
    private String password;
    private String nickName;
    private String salt;
    private String token;
}
