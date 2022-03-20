package com.cj.crm.controller;

import com.cj.crm.common.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jinmunan
 * 2022/3/20
 * 21:47
 */
//@RestController
@Controller
public class LoginController extends BaseController {
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
    public String main() {
        return "main";
    }
}
