package com.cj.crm.mapper;
import com.cj.common.base.BaseQuery;
import org.apache.ibatis.annotations.Param;

import com.cj.common.base.BaseMapper;
import com.cj.crm.entity.SaleChance;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
* @author THINKPAD
* @description 针对表【t_sale_chance】的数据库操作Mapper
* @createDate 2022-03-21 16:49:50
* @Entity com.cj.crm.entity.SaleChance
*/
public interface SaleChanceMapper extends BaseMapper<SaleChance, Integer> {

    /**
     * 多条件查询的接口不需要单独定义
     * 由于多个模块涉及到多条件查询操作，所以将对应的多条件查询功能定义在父接口BaseMapper
     */

}




