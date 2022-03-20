package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

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
        User tim = userMapper.selectByUsername("koko");
        System.out.println(tim);
    }

    @Test
    void updatePasswordByUid(){
        Integer row = userMapper.updatePasswordByUid("111111", 3, "管理员", new Date());
        System.out.println(row);

    }


    @Test
    void findByUid(){
        User user = userMapper.findByUid(3);
        System.out.println(user);

    }

    @Test
    void updateInfoByUid(){
        User user = new User();
        user.setUid(16);
        user.setPhone("123456789");
        user.setEmail("123@mail");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }
}