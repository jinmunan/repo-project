package com.cy.store.service;

import com.cy.store.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Date;

/**
 * @author THINKPAD
 * @description 针对表【t_user】的数据库操作Service
 * @createDate 2022-03-18 15:06:18
 */
public interface UserService extends IService<User> {
    /**
     * 用户注册方法
     *
     * @param user 用户的数据对象
     */
    void reg(User user);

    /**
     * 用户登录的方法
     *
     * @param username 用户名
     * @param password 密码
     */
    User login(String username, String password);

    /**
     * 修改密码
     */
    void changePassword(Integer uid, String username, String oldPassword, String password);

    /**
     * 更具用户的id查询用户的数据
     *
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 更新用户的数据操作
     *
     * @param uid      session
     * @param username session
     * @param user
     */
    void changeInfo(Integer uid, String username, User user);

    /**
     * 修改用户头像
     * @param uid
     * @param avatar 用户头像路径
     * @param username
     */
    void changeAvatar(Integer uid,String avatar,String username);

}
