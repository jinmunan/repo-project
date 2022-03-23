package com.cy.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.store.entity.DictDistrict;
import com.cy.store.mapper.DictDistrictMapper;
import com.cy.store.service.DictDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author THINKPAD
 * @description 针对表【t_dict_district】的数据库操作Service实现
 * @createDate 2022-03-23 11:13:06
 */
@Service
public class DictDistrictServiceImpl extends ServiceImpl<DictDistrictMapper, DictDistrict>
        implements DictDistrictService {

    @Autowired
    private DictDistrictMapper dictDistrictMapper;

    @Override
    public List<DictDistrict> getByParent(String parent) {
        /**
         * 网络数据传输过程中,尽量避免无效字段的传输.可以设置为null
         */
        List<DictDistrict> list = dictDistrictMapper.findByParent(parent);
        for (DictDistrict d : list) {
            d.setId(null);
            d.setParent(null);
        }
        return list;
    }

    @Override
    public String getNameByCode(String code) {
        return dictDistrictMapper.findNameByCode(code);
    }
}




