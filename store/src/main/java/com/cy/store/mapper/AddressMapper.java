package com.cy.store.mapper;

import java.util.Date;
import java.util.List;

import com.cy.store.entity.Address;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author THINKPAD
 * @description 针对表【t_address】的数据库操作Mapper
 * @createDate 2022-03-23 09:57:46
 * @Entity .entity.com.cy.store.entity.Address
 */
public interface AddressMapper extends BaseMapper<Address> {
    /**
     * 插入用户的收获地址
     *
     * @param address
     * @return
     */
    int insertSelective(Address address);


    /**
     * 查找用户收货地址的数量
     *
     * @param
     * @return
     */
    int countByUid(Integer uid);


    /**
     * 根据id查询列表
     *
     * @param uid
     * @return
     */
    List<Address> findByUid(@Param("uid") Integer uid);

    /**
     * 默认收获地址
     */
    Address findByAid(@Param("aid") Integer aid);


    /**
     * 将状态设置为非默认
     *
     * @param uid
     * @return
     */
    int updateIsDefaultByUid(@Param("uid") Integer uid);

    /**
     * 更新默认地址
     *
     * @param aid
     * @return
     */
    int updateIsDefaultByAid(@Param("aid") Integer aid,
                             @Param("modifiedUser") String modifiedUser,
                             @Param("modifiedTime") Date modifiedTime);


    /**
     * 当删除的是默认的地址,将安装最近修改的地址作为默认地址
     * @return
     */
    Address selectAddressByModifiedTime(Integer uid);

    /**
     * 删除
     * @param aid
     * @return
     */
    Integer deleteByAid(Integer aid);

    /**
     * 查询最近修改的地址
     * @param uid
     * @return
     */
    Address findLastModified(Integer uid);
}




