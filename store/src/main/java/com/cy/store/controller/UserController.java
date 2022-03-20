package com.cy.store.controller;

import com.cy.common.utils.JsonResult;
import com.cy.store.entity.User;
import com.cy.store.service.UserService;
import com.cy.store.service.exception.InsertException;
import com.cy.store.service.exception.UsernameDuplicatedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by Jinmunan
 * 2022/3/18
 * 17:15
 */
@Slf4j
@RestController
@RequestMapping(value = "/users") //将用户注册功能放到user模块
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/reg")
    //@ResponseBody 表示将响应结果以json格式传到前端
    public JsonResult<Void> reg(User user) {
        //创建响应结果的对象
        userService.reg(user);
        return new JsonResult<>(OK);
    }

    @RequestMapping(value = "/login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User user = userService.login(username, password);
        /*向session对象中完成数据的绑定(session是全局的)*/
        session.setAttribute("uid", user.getUid());
        session.setAttribute("username", user.getUsername());
        log.debug("设置用户的uid至session：" + String.valueOf(getUidFromSession(session)));
        log.debug("设置用户的username至session：" + getUsernameFromSession(session));
        return new JsonResult<User>(OK, user);
    }

    @RequestMapping(value = "/changePassword")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping(value = "/getByUid")
    public JsonResult<User> getByUid(HttpSession session) {
        User user = userService.getById(getUidFromSession(session));
        return new JsonResult<User>(OK, user);
    }

    @RequestMapping(value = "/changeInfo")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid, username, user);
        return new JsonResult<>(OK);
    }
}
