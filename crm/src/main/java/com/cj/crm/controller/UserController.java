package com.cj.crm.controller;

import com.cj.common.base.BaseController;
import com.cj.common.base.ResultInfo;
import com.cj.common.utils.LoginUserUtil;
import com.cj.crm.model.UserModel;
import com.cj.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Jinmunan
 * 2022/3/21
 * 8:59
 * 因为全局异常的关系 必须使用@Controller 而不是@RestController
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

    @RequestMapping(value = "updatePassword")
    @ResponseBody
    public ResultInfo updatePassword(HttpServletRequest request, String oldPassword, String newPassword, String confirmPassword) {
        ResultInfo resultInfo = new ResultInfo();
        userService.updateUserPassword(LoginUserUtil.releaseUserIdFromCookie(request), oldPassword, newPassword, confirmPassword);
        return resultInfo;
    }

    @RequestMapping(value = "/queryAllSales")
    @ResponseBody
    public List<Map<String, Object>> queryAllSales() {
        return userService.queryAllSales();
    }


    /**
     * 密码修改页面
     * @return
     */
    @RequestMapping(value = "/toPasswordPage")
    public String toPasswordPage() {
        return "user/password";
    }
}
