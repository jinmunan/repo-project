package com.cj.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cj.acl.entity.User;
import com.cj.acl.mapper.UserMapper;
import com.cj.acl.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by Jinmunan
 * 2022/4/13
 * 14:27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User selectByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }
}
