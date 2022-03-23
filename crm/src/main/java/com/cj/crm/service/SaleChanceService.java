package com.cj.crm.service;


import com.cj.crm.entity.SaleChance;
import com.cj.crm.query.SaleChanceQuery;

import java.util.Map;

/**
 * @author THINKPAD
 * @description 针对表【t_sale_chance】的数据库操作Service
 * @createDate 2022-03-21 16:49:50
 */
public interface SaleChanceService {
    Map<String, Object> querySaleChanceByParams(SaleChanceQuery saleChanceQuery);
    void saveSaleChance(SaleChance saleChance);
    void updateSaleChance(SaleChance saleChance);
    SaleChance selectByPrimaryKey(Integer id);
    void deleteSaleChance(Integer[] ids);
}
