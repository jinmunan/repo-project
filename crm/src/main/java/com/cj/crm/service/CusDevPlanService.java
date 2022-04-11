package com.cj.crm.service;

import com.cj.crm.common.base.BaseService;
import com.cj.crm.common.utils.AssertUtil;
import com.cj.crm.entity.CusDevPlan;
import com.cj.crm.entity.Role;
import com.cj.crm.mapper.CusDevPlanMapper;
import com.cj.crm.mapper.SaleChanceMapper;
import com.cj.crm.query.CusDevPlanQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author THINKPAD
 * @description 针对表【t_cus_dev_plan】的数据库操作Service
 * @createDate 2022-03-21 16:50:09
 */
@Service
public class CusDevPlanService extends BaseService<CusDevPlan, Integer> {

    @Autowired
    private SaleChanceMapper saleChanceMapper;


    public Map<String, Object> queryCusDevPlansByParams(CusDevPlanQuery cusDevPlanQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(cusDevPlanQuery.getPage(), cusDevPlanQuery.getLimit());
        PageInfo<CusDevPlan> info = new PageInfo<CusDevPlan>(selectByParams(cusDevPlanQuery));
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", info.getTotal()); //count
        map.put("data", info.getList());
        return map;
    }

    /**
     * 开发计划添加
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveCusDevPlan(CusDevPlan cusDevPlan) {
        /**
         * 参数校验
         * 默认参数
         * 默认密码设置
         * */
        checkParams(cusDevPlan.getSaleChanceId(), cusDevPlan.getPlanItem(), cusDevPlan.getPlanDate());
        // 2. 设置参数默认值
        cusDevPlan.setIsValid(1);
        cusDevPlan.setCreateDate(new Date());
        cusDevPlan.setUpdateDate(new Date());
        // 3. 执行添加，判断结果
        AssertUtil.isTrue(insertSelective(cusDevPlan) < 1, "计划项记录添加失败！");
    }

    /**
     * 参数校验
     *
     * @param saleChanceId
     * @param planItem
     * @param planDate
     */
    private void checkParams(Integer saleChanceId, String planItem, Date planDate) {
        AssertUtil.isTrue(null == saleChanceId || saleChanceMapper.selectByPrimaryKey(saleChanceId) == null, "请设置营销机会ID！");
        AssertUtil.isTrue(StringUtils.isBlank(planItem), "请输入计划项内容！");
        AssertUtil.isTrue(null == planDate, "请指定计划项日期！");
    }

    /**
     * 开发计划更新
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCusDevPlan(CusDevPlan cusDevPlan) {
        /**
         * 参数校验
         * 默认参数
         * 默认密码设置
         * */
        AssertUtil.isTrue(null == cusDevPlan.getId() || null == selectByPrimaryKey(cusDevPlan.getId()), "待更新记录不存在!");
        // 1. 参数校验
        checkParams(cusDevPlan.getSaleChanceId(), cusDevPlan.getPlanItem(), cusDevPlan.getPlanDate());
        // 2. 设置参数默认值
        cusDevPlan.setUpdateDate(new Date());
        // 3. 执行添加，判断结果
        AssertUtil.isTrue(updateByPrimaryKeySelective(cusDevPlan) < 1, "计划项记录更新失败!");
    }


    /**
     * 开发机会删除
     *
     * @param id
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void delCusDevPlan(Integer id) {
        CusDevPlan cusDevPlan = selectByPrimaryKey(id);
        AssertUtil.isTrue(null == id || null == cusDevPlan, "待删除记录不存在!");
        cusDevPlan.setIsValid(0);
        AssertUtil.isTrue(updateByPrimaryKeySelective(cusDevPlan) < 1, "计划项记录删除失败 !");
    }
}
