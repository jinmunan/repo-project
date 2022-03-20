package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.service.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Jinmunan
 * 2022/3/18
 * 15:54
 */
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void reg() {
        try {
            User user = new User();
            user.setUsername("mimi");
            user.setPassword("123456");
            userService.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            /*获取类的对象,在获取类的名称*/
            System.out.println(e.getClass().getSimpleName());
            /*获取异常的具体描述信息*/
            System.out.println(e.getMessage());
        }
    }

    @Test
    void salt() {
        String salt = UUID.randomUUID().toString().toLowerCase().replace("-", "");//补全非关键信息数据
        System.out.println(salt);
    }

    @Test
    void login() {
        User lili = userService.login("lili", "123456");
        System.out.println(lili);
    }

    @Test
    void changePassword() {
        userService.changePassword(14, "sasa", "123456", "111111");
    }

    @Test
    void getById(){
        User user = userService.getById(16);
        System.out.println(user);
    }

    @Test
    void changeInfo(){
        User user = new User();
        user.setPhone("123");
        user.setEmail("123");
        user.setModifiedUser("123");
        user.setModifiedTime(new Date());
        userService.changeInfo(16,"oo",user);
    }
}