package com.xxxx.crm.service;

import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.UserRoleMapper;
import com.xxxx.crm.vo.UserRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 乐字节：专注线上IT培训
 * 答疑老师微信：lezijie
 */
@Service
public class UserRoleService extends BaseService<UserRole, Integer> {

    @Resource
    private UserRoleMapper userRoleMapper;
}
