package com.cy.store.mapper;

import java.util.Date;

import com.cy.store.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/*
  @author THINKPAD
 * @description 针对表【t_user】的数据库操作Mapper
 * @createDate 2022-03-18 15:06:18
 * @Entity com.cy.store.entity.User
 */

/**
 * 用户模块的吃就成接口
 */

public interface UserMapper extends BaseMapper<User> {
    /**
     * 插入用户数据
     *
     * @param user 用户数据
     * @return 受影响行数
     */
    Integer insertUser(User user);

    /**
     * 根据用户名查询用户的数据
     *
     * @param username 用户名
     * @return 返回这个用户的数据, 没有则返回null
     */
    User selectByUsername(@Param("username") String username);


    /**
     * 根据用户的uid来修改用户的密码
     *
     * @param password
     * @param uid
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updatePasswordByUid(
            @Param("password") String password
            , @Param("uid") Integer uid
            , String modifiedUser
            , Date modifiedTime);

    /**
     * 查询用户的id查询用户的数据 相当于回传数据
     *
     * @param uid id
     * @return 找到返回对象，反之返回null值
     */
    User findByUid(@Param("uid") Integer uid);

    /**
     * 个人信息修改
     *
     * @param user
     * @return
     */
    Integer updateInfoByUid(User user);

    /**
     * @param avatar
     * @param uid
     * @return
     */
    Integer updateAvatarByUid(
            @Param("avatar") String avatar,
            @Param("uid") Integer uid,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);
}




