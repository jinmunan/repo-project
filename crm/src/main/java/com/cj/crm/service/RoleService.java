package com.cj.crm.service;


import com.cj.crm.common.base.BaseService;
import com.cj.crm.common.utils.AssertUtil;
import com.cj.crm.common.utils.Md5Util;
import com.cj.crm.common.utils.PhoneUtil;
import com.cj.crm.entity.Role;
import com.cj.crm.entity.User;
import com.cj.crm.mapper.RoleMapper;
import com.cj.crm.mapper.UserRoleMapper;
import com.cj.crm.query.RoleQuery;
import com.cj.crm.query.UserQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author THINKPAD
 * @description 针对表【t_role】的数据库操作Service
 * @createDate 2022-04-11 08:50:27
 */
@Service
public class RoleService extends BaseService<Role, Integer> {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 查询所有权限
     *
     * @param userId
     * @return
     */
    public List<Map<String, Object>> queryAllRoles(Integer userId) {
        return roleMapper.queryAllRoles(userId);
    }

    /**
     * 角色模糊查询
     *
     * @param roleQuery
     * @return
     */
    public Map<String, Object> queryRoleByParams(RoleQuery roleQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(roleQuery.getPage(), roleQuery.getLimit());
        PageInfo<Role> info = new PageInfo<>(roleMapper.selectByParams(roleQuery));
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", info.getTotal()); //count
        map.put("data", info.getList());
        return map;
    }

    /**
     * 角色添加
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveRole(Role role) {
        /**
         * 参数校验
         * 默认参数
         * 默认密码设置
         * */
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()), "请输入角色名");
        AssertUtil.isTrue(null != roleMapper.queryRoleByRoleName(role.getRoleName()), "该角色已存在!");
        role.setIsValid(1);
        role.setCreateDate(new Date());
        role.setUpdateDate(new Date());
        AssertUtil.isTrue(insertSelective(role) < 1, "添加角色信息失败");
    }

    /**
     * 角色更新
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateRole(Role role) {
        /**
         * 参数校验
         * 默认参数
         * 默认密码设置
         * */
        Role temp = roleMapper.queryRoleByRoleName(role.getRoleName());
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()), "请输入角色名");
        AssertUtil.isTrue(null != temp && !temp.getId().equals(role.getId()), "该角色已存在!");
        role.setUpdateDate(new Date());
        AssertUtil.isTrue(updateByPrimaryKeySelective(role) < 1, "更新角色信息失败");
    }

    /**
     * 角色删除
     */
    public void deleteRole(Integer roleId) {
        Role role = selectByPrimaryKey(roleId);
        AssertUtil.isTrue(null == role, "待删除的数据不存在");
        //根据角色id删除用户角色信息
        int total = userRoleMapper.countUserRoleByRoleId(roleId);
        /*删除角色信息表的数据*/
        if (total > 0) {
            AssertUtil.isTrue(userRoleMapper.deleteUserRoleByRoleId(roleId) != total, "用户角色记录删除失败!");
        }
        role.setIsValid(0);
        AssertUtil.isTrue(updateByPrimaryKeySelective(role) < 1, "角色记录删除失败!");
    }
}

