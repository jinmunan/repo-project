package com.cy.store.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.store.entity.DictDistrict;

/**
* @author THINKPAD
* @description 针对表【t_dict_district】的数据库操作Mapper
* @createDate 2022-03-23 11:13:06
* @Entity .entity.com.cy.store.entity.DictDistrict
*/
public interface DictDistrictMapper extends BaseMapper<DictDistrict> {
    //查询省市区
    List<DictDistrict> findByParent(String parent);

    //查询省名名字
    String findNameByCode(String code);

}




