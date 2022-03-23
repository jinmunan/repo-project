package com.cy.store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.store.entity.DictDistrict;

import java.util.List;

/**
 * @author THINKPAD
 * @description 针对表【t_dict_district】的数据库操作Service
 * @createDate 2022-03-23 11:13:06
 */
public interface DictDistrictService extends IService<DictDistrict> {
    //省市区
    List<DictDistrict> getByParent(String parent);

    //查询省名名字
    String getNameByCode(String code);
}
