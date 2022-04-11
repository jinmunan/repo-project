package com.cj.crm.mapper;

import com.cj.crm.common.base.BaseMapper;
import com.cj.crm.entity.Role;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleMapper extends BaseMapper<Role, Integer> {

    List<Map<String, Object>> queryAllRoles(Integer userId);

    Role queryRoleByRoleName(String roleName);


}