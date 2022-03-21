package com.cj.crm.controller;

import com.cj.common.base.BaseController;
import com.cj.common.base.ResultInfo;
import com.cj.common.exceptions.ParamsException;
import com.cj.crm.model.UserModel;
import com.cj.crm.service.UserService;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jinmunan
 * 2022/3/21
 * 8:59
 */
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login")
    public ResultInfo login(String username, String password) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            UserModel model = userService.login(username, password);
            /**
             * 用户登录成功后
             * 1.将信息存到cookie
             * 2.将信息存到session
             */
            resultInfo.setResult(model);
        } catch (ParamsException e) {
            e.printStackTrace();
            resultInfo.setCode(e.getCode());
            resultInfo.setMsg(e.getMsg());
            //System.out.println(e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(500);
            resultInfo.setResult("操作失败!");
        }
        return resultInfo;
    }

}
