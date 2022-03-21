package com.cj.crm.mapper;

import com.cj.common.utils.Md5Util;
import com.cj.crm.entity.User;
import com.cj.crm.mapper.UserMapper;
import net.bytebuddy.build.ToStringPlugin;
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

    @Test
    void findById(){
        User user = userMapper.findById(10);
        System.out.println(user);
    }

    @Test
    void updateSelective(){
        User user = userMapper.findById(80);
        user.setPassword(Md5Util.encode("222222"));
        userMapper.updateSelective(user);
    }
}