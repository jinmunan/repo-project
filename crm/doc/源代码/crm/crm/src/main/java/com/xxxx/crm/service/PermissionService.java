package com.xxxx.crm.service;

import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.PermissionMapper;
import com.xxxx.crm.vo.Permission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 乐字节：专注线上IT培训
 * 答疑老师微信：lezijie
 */
@Service
public class PermissionService extends BaseService<Permission,Integer> {

    @Resource
    private PermissionMapper permissionMapper;


    /***
     * 通过查询用户拥有的角色，角色拥有的资源，得到用户拥有的资源列表 （资源权限码）
     *
     *
     * 乐字节：专注线上IT培训
     * 答疑老师微信：lezijie
     * @param userId
     * @return java.util.List<java.lang.String>
     */
    public List<String> queryUserHasRoleHasPermissionByUserId(Integer userId) {
        return permissionMapper.queryUserHasRoleHasPermissionByUserId(userId);
    }
}
