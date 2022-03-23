package com.cy.store.mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.store.entity.Address;

/**
* @author THINKPAD
* @description 针对表【t_address】的数据库操作Mapper
* @createDate 2022-03-23 09:57:46
* @Entity .entity.com.cy.store.entity.Address
*/
public interface AddressMapper extends BaseMapper<Address> {
    /**
     * 插入用户的收获地址
     * @param address
     * @return
     */
    int insertSelective(Address address);


    /**
     * 查找用户收货地址的数量
     * @param aid
     * @return
     */
    int countByUid(Integer uid);
}




