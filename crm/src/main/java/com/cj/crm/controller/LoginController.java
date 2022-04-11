package com.cj.crm.controller;

import com.cj.crm.common.base.BaseController;
import com.cj.crm.common.utils.LoginUserUtil;
import com.cj.crm.entity.User;
import com.cj.crm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jinmunan
 * 2022/3/20
 * 21:47
 */
@SuppressWarnings("all")
//@RestController 不能使用
@Controller
public class LoginController extends BaseController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 系统登录页
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    /**
     * 系统界面欢迎页
     */
    @RequestMapping(value = "/welcome")
    public String welcome() {
        return "welcome";
    }

    /**
     * 后端管理主页面
     *
     * @return
     */
    @RequestMapping(value = "/main")
    public String main(HttpServletRequest request) {
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userMapper.selectByPrimaryKey(userId);
        //设置到请求域中
        request.setAttribute("user", user);
        return "main";
    }
}
