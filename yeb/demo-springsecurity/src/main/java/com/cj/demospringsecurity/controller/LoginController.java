package com.cj.demospringsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Jinmunan
 * 2022/4/11
 * 20:37
 */
@Controller
public class LoginController {


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login() {
        System.out.println("执行登录方法");
        return "main.html";
    }
}
