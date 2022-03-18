package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Jinmunan
 * 2022/3/18
 * 15:54
 */
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void insertUser() {
        User user = new User();
        user.setUsername("tim");
        user.setPassword("123456");
        Integer row = userMapper.insertUser(user);
        System.out.println(row);

    }

    @Test
    void selectByUsername() {
        User tim = userMapper.selectByUsername("tim");
        System.out.println(tim);
    }
}