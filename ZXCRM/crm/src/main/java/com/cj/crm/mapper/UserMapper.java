package com.cj.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cj.crm.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author THINKPAD
 * @description 针对表【t_user】的数据库操作Mapper
 * @createDate 2022-03-21 10:25:05
 * @Entity com.cj.crm.entity.User
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询用户
     * @param username
     * @return
     */
    User findByUserName(@Param("username") String username);
}




