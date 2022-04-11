package com.cj.crm.service;

import com.cj.crm.common.base.BaseService;
import com.cj.crm.entity.CusDevPlan;
import com.cj.crm.query.CusDevPlanQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author THINKPAD
 * @description 针对表【t_cus_dev_plan】的数据库操作Service
 * @createDate 2022-03-21 16:50:09
 */
@Service
public class CusDevPlanService extends BaseService<CusDevPlan, Integer> {

    public Map<String, Object> queryCusDevPlansByParams(CusDevPlanQuery cusDevPlanQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(cusDevPlanQuery.getPage(), cusDevPlanQuery.getLimit());
        PageInfo<CusDevPlan> info = new PageInfo<CusDevPlan>(selectByParams(cusDevPlanQuery));
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", info.getTotal()); //count
        map.put("data", info.getList());
        System.out.println(info.getList());
        return map;
    }
}
