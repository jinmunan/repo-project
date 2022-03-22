package com.cj.crm.service.impl;

import com.cj.common.base.BaseService;
import com.cj.crm.entity.SaleChance;
import com.cj.crm.mapper.SaleChanceMapper;
import com.cj.crm.query.SaleChanceQuery;
import com.cj.crm.service.SaleChanceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author THINKPAD
 * @description 针对表【t_sale_chance】的数据库操作Service实现
 * @createDate 2022-03-21 16:49:50
 */
@Service
public class SaleChanceServiceImpl extends BaseService<SaleChance, Integer> implements SaleChanceService {

    @Autowired
    private SaleChanceMapper saleChanceMapper;


    @Override
    public Map<String, Object> querySaleChanceByParams(SaleChanceQuery saleChanceQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(saleChanceQuery.getPage(), saleChanceQuery.getLimit());
        PageInfo<SaleChance> info = new PageInfo<>(saleChanceMapper.selectByParams(saleChanceQuery));
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", info.getTotal()); //count
        map.put("data", info.getList());
        return map;
    }
}

