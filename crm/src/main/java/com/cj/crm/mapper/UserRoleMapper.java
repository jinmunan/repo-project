package com.cj.crm.mapper;

import com.cj.crm.common.base.BaseMapper;
import com.cj.crm.entity.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleMapper extends BaseMapper<UserRole, Integer> {

    int countUserRoleByUserId(int userId);

    int deleteUserRoleByUserId(int userId);

    int countUserRoleByRoleId(Integer roleId);

    int deleteUserRoleByRoleId(Integer roleId);
}