package com.cj.crm.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cj.common.utils.AssertUtil;
import com.cj.common.utils.Md5Util;
import com.cj.common.utils.UserIDBase64;
import com.cj.crm.entity.User;
import com.cj.crm.mapper.UserMapper;
import com.cj.crm.model.UserModel;
import com.cj.crm.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author THINKPAD
 * @description 针对表【t_user】的数据库操作Service实现
 * @createDate 2022-03-21 10:25:05
 */
@SuppressWarnings("all")
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     * 1. 参数判断，判断用户姓名、用户密码非空
     * 如果参数为空，抛出异常（异常被控制层捕获并处理）
     * 2. 调用数据访问层，通过用户名查询用户记录，返回用户对象
     * 3. 判断用户对象是否为空
     * 如果对象为空，抛出异常（异常被控制层捕获并处理）
     * 4. 判断密码是否正确，比较客户端传递的用户密码与数据库中查询的用户对象中的用户密码
     * 如果密码不相等，抛出异常（异常被控制层捕获并处理）
     * 5. 如果密码正确，登录成功
     */
    public UserModel login(String username, String password) {
        // 1. 参数判断，判断用户姓名、用户密码非空
        checkLoginParams(username, password);
        // 2. 调用数据访问层，通过用户名查询用户记录，返回用户对象
        User user = userMapper.findByUserName(username);
        // 3. 判断用户对象是否为空
        AssertUtil.isTrue(user == null, "用户姓名不存在！");
        // 4. 判断密码是否正确，比较客户端传递的用户密码与数据库中查询的用户对象中的用户密码
        String MD5Password = Md5Util.encode(password);
        AssertUtil.isTrue(!user.getPassword().equals(MD5Password), "密码错误！");
        // 返回构建用户对象
        return buildUserInfo(user);
    }

    /**
     * 校验参数是否为空
     *
     * @param userName
     * @param userPwd
     */
    private void checkLoginParams(String userName, String userPwd) {
        // 验证用户姓名
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户姓名不能为空！");
        // 验证用户密码
        AssertUtil.isTrue(StringUtils.isBlank(userPwd), "用户密码不能为空！");
    }


    /**
     * 绑定传输对象
     * @param user
     * @return
     */
    private UserModel buildUserInfo(User user) {
        UserModel userModel = new UserModel();
        //userModel.setUserId(user.getId());
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userModel.setUsername(user.getUsername());
        userModel.setTrueName(user.getTruename());
        return userModel;
    }
}




