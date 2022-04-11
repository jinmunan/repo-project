package com.cj.crm.controller;

import com.cj.crm.common.base.BaseController;
import com.cj.crm.common.base.ResultInfo;
import com.cj.crm.common.utils.LoginUserUtil;
import com.cj.crm.entity.User;
import com.cj.crm.model.UserModel;
import com.cj.crm.query.UserQuery;
import com.cj.crm.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Jinmunan
 * 2022/3/21
 * 8:59
 * 因为视图返回的原因 必须使用@Controller 而不是@RestController
 */
@SuppressWarnings("all")
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public ResultInfo login(String username, String password) {
        ResultInfo resultInfo = new ResultInfo();
        UserModel model = userService.login(username, password);
        /**
         * 用户登录成功后
         * 1.将信息存到cookie
         * 2.将信息存到session
         */
        resultInfo.setResult(model);
        return resultInfo;
    }

    @RequestMapping(value = "update_password")
    @ResponseBody
    public ResultInfo updatePassword(HttpServletRequest request, String oldPassword, String newPassword, String confirmPassword) {
        ResultInfo resultInfo = new ResultInfo();
        userService.updateUserPassword(LoginUserUtil.releaseUserIdFromCookie(request), oldPassword, newPassword, confirmPassword);
        return resultInfo;
    }

    @RequestMapping(value = "/query_all_sales")
    @ResponseBody
    public List<Map<String, Object>> queryAllSales() {
        return userService.queryAllSales();
    }


    /**
     * 密码修改页面
     *
     * @return
     */
    @RequestMapping(value = "/toPasswordPage")
    public String toPasswordPage() {
        return "user/password";
    }


    /**
     * 用户模糊
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> queryUsersByParams(UserQuery userQuery) {
        return userService.queryUserByParams(userQuery);
    }

    /**
     * 用户视图页面
     */
    @RequestMapping("index")
    public String index() {
        return "user/user";
    }

    /**
     * 用户新增
     *
     * @return
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public ResultInfo saveUser(User user) {
        userService.saveUser(user);
        return success("用户记录新增成功");
    }

    /**
     * 用户修改
     *
     * @return
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public ResultInfo updateUser(User user) {
        userService.updateUser(user);
        return success("用户记录更新成功");
    }

    /**
     * 添加或新增用户视图页面
     */
    @RequestMapping("toAddOrUpdateUserPage")
    public String addOrUpdateUserPage(Integer id, Model model) {
        model.addAttribute("userInfo", userService.selectByPrimaryKey(id));
        return "user/add_update";
    }

    /**
     * 用户删除
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteUser(Integer[] ids) {
        userService.deleteUserByIds(ids);
        return success("用户数据删除成功!");
    }
}
