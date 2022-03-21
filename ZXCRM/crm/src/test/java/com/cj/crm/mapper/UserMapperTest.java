package com.cj.crm.mapper;

import com.cj.crm.entity.User;
import com.cj.crm.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * Created by Jinmunan
 * 2022/3/21
 * 9:28
 */
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void findByUserName() {
        User user = userMapper.findByUserName("admin");
        System.out.println(user);
    }
}