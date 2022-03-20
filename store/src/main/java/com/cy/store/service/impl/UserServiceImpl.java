package com.cy.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.common.utils.MD5Util;
import com.cy.store.entity.User;
import com.cy.store.service.UserService;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author THINKPAD
 * @description 针对表【t_user】的数据库操作Service实现
 * @createDate 2022-03-18 15:06:18
 */
@Slf4j
@Service
@SuppressWarnings("all")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        //检测用户名是否被注册
        User result = userMapper.selectByUsername(user.getUsername());
        //判断结果是否为null
        if (result != null) throw new UsernameDuplicatedException("用户名被占用");
        //密码加密
        String password = user.getPassword();
        //生成盐值
        String salt = UUID.randomUUID().toString().toLowerCase().replace("-", "");
        //加盐后的加密字符串
        String newPassword = MD5Util.getMD5Password(password, salt);
        user.setPassword(newPassword);
        user.setSalt(salt);
        //补全非关键信息数据
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        //执行注册业务成功的实现
        Integer rows = userMapper.insertUser(user);
        if (rows != 1) throw new InsertException("在用户注册过程中产生了未知的异常");
    }

    @Override
    public User login(String username, String password) {
        //通过用户名查询用户信息
        User result = userMapper.selectByUsername(username);
        if (result == null) throw new UserNotFoundException("用户数据不存在");
        //判断密码是否正确
        /*将用户密码进行加密*/
        String md5Password = MD5Util.getMD5Password(password, result.getSalt());
        if (!md5Password.equals(result.getPassword())) throw new PasswordNotMatchException("用户密码错误");
        //判断is_delete是否为1表示被标志删除
        if (result.getIsDelete() == 1) throw new UserNotFoundException("用户数据不存在");
        //提升性能
        User user = new User();
        user.setUsername(result.getUsername());
        user.setUid(result.getUid());
        user.setAvatar(result.getAvatar());
        //登录成功
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String password) {
        //查询用户存不存在
        User user = userMapper.findByUid(uid);
        if (user == null || user.getIsDelete() == 1) throw new UserNotFoundException("用户数据不存在");
        //原始密码与数据库密码对比
        if (!MD5Util.getMD5Password(oldPassword, user.getSalt()).equals(user.getPassword()))
            throw new PasswordNotMatchException("密码错误");
        //将新的密码设置到数据库中,需要加密
        String md5Password = MD5Util.getMD5Password(password, user.getSalt());
        Integer row = userMapper.updatePasswordByUid(md5Password, uid, user.getUsername(), new Date());
        if (row != 1) throw new UpdateException("更新数据产生位置的异常");
    }

    @Override
    public User getById(Integer id) {
        User result = userMapper.findByUid(id);
        if (result == null || result.getIsDelete() == 1) throw new UserNotFoundException("用户数据不存在");
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) throw new UserNotFoundException("用户数据不存在");
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer row = userMapper.updateInfoByUid(user);
        if (row != 1) throw new UserNotFoundException("更新数据时产生未知的异常");
    }

}




