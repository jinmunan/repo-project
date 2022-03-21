package com.cj.crm.service;

import com.cj.crm.model.UserModel;
import com.cj.crm.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Jinmunan
 * 2022/3/21
 * 10:48
 */
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void login() {
        UserModel user = userService.login("admin", "123456");
        System.out.println(user);
    }
}