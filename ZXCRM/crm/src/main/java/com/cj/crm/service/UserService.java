package com.cj.crm.service;

import com.cj.crm.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cj.crm.model.UserModel;

/**
 * @author THINKPAD
 * @description 针对表【t_user】的数据库操作Service
 * @createDate 2022-03-21 10:25:05
 */
public interface UserService extends IService<User> {
    UserModel login(String username, String password);

    User findById(Integer id);

    void updateUserPassword(Integer userId, String oldPassword, String newPassword, String confirmPassword);
}
