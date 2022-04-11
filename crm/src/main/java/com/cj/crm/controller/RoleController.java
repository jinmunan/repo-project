package com.cj.crm.controller;

import com.cj.crm.common.base.BaseController;
import com.cj.crm.common.base.ResultInfo;
import com.cj.crm.entity.Role;
import com.cj.crm.entity.User;
import com.cj.crm.query.RoleQuery;
import com.cj.crm.query.UserQuery;
import com.cj.crm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Jinmunan
 * 2022/4/11
 * 9:31
 */
@Controller
@RequestMapping("role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;


    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map<String, Object>> queryAllRoles(Integer userId) {
        return roleService.queryAllRoles(userId);
    }


    /**
     * 角色模糊
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> queryRoleByParams(RoleQuery roleQuery) {
        return roleService.queryRoleByParams(roleQuery);
    }


    /**
     * 角色视图页面
     */
    @RequestMapping("index")
    public String index() {
        return "role/role";
    }


    /**
     * 角色新增
     *
     * @return
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public ResultInfo saveRole(Role role) {
        roleService.saveRole(role);
        return success("角色记录新增成功");
    }

    /**
     * 角色修改
     *
     * @return
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public ResultInfo updateRole(Role role) {
        roleService.updateRole(role);
        return success("角色记录更新成功");
    }

    /**
     * 添加或新增角色视图页面
     */
    @RequestMapping("toAddOrUpdateRolePage")
    public String toAddOrUpdateRolePage(Integer roleId, Model model) {
        model.addAttribute("role", roleService.selectByPrimaryKey(roleId));
        return "role/add_update";
    }


    /**
     * 角色删除
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteUser(Integer roleId) {
        roleService.deleteRole(roleId);
        return success("角色数据删除成功!");
    }
}
