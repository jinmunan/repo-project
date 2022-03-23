package com.cy.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jinmunan
 * 2022/3/18
 * 14:59
 * 实体类共有的基类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity implements Serializable {
    private String createdUser; //创建用户
    private Date createdTime; //创建时间
    private String modifiedUser; //修改用户
    private Date modifiedTime; //修改时间
}
