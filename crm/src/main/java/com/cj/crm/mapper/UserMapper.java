package com.cj.crm.mapper;

import com.cj.crm.common.base.BaseMapper;
import com.cj.crm.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author THINKPAD
 * @description 针对表【t_user】的数据库操作Mapper
 * @createDate 2022-03-21 10:25:05
 * @Entity com.cj.crm.entity.User
 */
@SuppressWarnings("all")
@Repository
public interface UserMapper extends BaseMapper<User, Integer> {
    /**
     * 返回下拉框用户,销售人员
     */
    List<Map<String, Object>> queryAllSales();

    /**
     * 通过用户名查询用户记录，返回用户对象
     */
    User queryUserByName(String userName);

    /**
     * 查询所有的客户经理
     *
     * @return
     */
    List<Map<String, Object>> queryAllCustomerManagers();
}




