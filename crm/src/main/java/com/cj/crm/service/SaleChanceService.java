package com.cj.crm.service;


import com.cj.crm.common.base.BaseService;
import com.cj.crm.common.utils.AssertUtil;
import com.cj.crm.common.utils.PhoneUtil;
import com.cj.crm.entity.SaleChance;
import com.cj.crm.enums.DevResult;
import com.cj.crm.enums.StateStatus;
import com.cj.crm.mapper.SaleChanceMapper;
import com.cj.crm.query.SaleChanceQuery;
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
 * @description 针对表【t_sale_chance】的数据库操作Service
 * @createDate 2022-03-21 16:49:50
 */
@Service
public class SaleChanceService extends BaseService<SaleChance, Integer> {
    @Autowired
    private SaleChanceMapper saleChanceMapper;

    /**
     * 多条件模糊查询
     *
     * @param saleChanceQuery
     * @return
     */
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

    /**
     * 营销机会添加
     */
    public void saveSaleChance(SaleChance saleChance) {
        //参数校验 客户 联系人 手机号
        checkParams(saleChance.getCustomerName(), saleChance.getLinkMan(), saleChance.getLinkPhone());

        /**相关参数设置 默认未分配 未开发 如果选择分配人  则 已分配 开发中*/
        saleChance.setState(StateStatus.UNSTATE.getType());
        saleChance.setDevResult(DevResult.UNDEV.getStatus());
        //已分配
        if (StringUtils.isNotBlank(saleChance.getAssignMan())) {
            saleChance.setState(StateStatus.STATED.getType());
            saleChance.setDevResult(DevResult.DEVING.getStatus());
            saleChance.setAssignTime(new Date());
        }
        saleChance.setIsValid(1);
        saleChance.setCreateDate(new Date());
        saleChance.setUpdateDate(new Date());
        AssertUtil.isTrue(saleChanceMapper.insertSelective(saleChance) < 1, "营销机会添加数据失败!");
    }

    /**
     * 参数校验
     */
    private void checkParams(String customerName, String linkMan, String phone) {
        AssertUtil.isTrue(StringUtils.isBlank(customerName), "客户名不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(linkMan), "联系人名不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(phone), "手机号为空!");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone), "手机号不合法!");
    }

    /**
     * 更新操作
     */
    public void updateSaleChance(SaleChance saleChance) {
        /**参数校验 id 客户 联系人 手机号*/
        SaleChance result = saleChanceMapper.selectByPrimaryKey(saleChance.getId());
        AssertUtil.isTrue(null == result, "待更新记录不存在!");
        checkParams(saleChance.getCustomerName(), saleChance.getLinkMan(), saleChance.getLinkPhone());
        /**相关参数设置
         *  未分配 -> 已分配
         *      state 0 -> 1
         *      assignTime -> new Date
         *      devResult 0 -> 1
         *  已分配 -> 未分配
         *      state 1 -> 0
         *      assignTime -> null
         *      devResult 1 -> 0
         * */
        saleChance.setUpdateDate(new Date());
        /**未分配 -> 已分配*/
        if (StringUtils.isBlank(result.getAssignMan()) && StringUtils.isNotBlank(saleChance.getAssignMan())) {
            saleChance.setState(StateStatus.STATED.getType());
            saleChance.setAssignTime(new Date());
            saleChance.setDevResult(DevResult.DEVING.getStatus());
            /**已分配 -> 未分配*/
        } else if (StringUtils.isNotBlank(result.getAssignMan()) && StringUtils.isBlank(saleChance.getAssignMan())) {
            saleChance.setState(StateStatus.UNSTATE.getType());
            saleChance.setAssignTime(null);
            saleChance.setDevResult(DevResult.UNDEV.getStatus());
            saleChance.setAssignMan("");
        }

        AssertUtil.isTrue(saleChanceMapper.updateByPrimaryKeySelective(saleChance) < 1, "营销机会更新数据失败!");
    }

    /**
     * 删除操作
     *
     * @param ids
     */
    public void deleteSaleChance(Integer[] ids) {
        AssertUtil.isTrue(null == ids || ids.length == 0, "请选择待删除的数据");
        Integer rows = saleChanceMapper.deleteBatch(ids);
        AssertUtil.isTrue(rows != ids.length, "记录删除失败");
    }

    /**
     * 更新开发状态
     *
     * @param id
     * @param devResult
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateSaleChanceDevResult(Integer id, Integer devResult) {
        AssertUtil.isTrue(null == id, "待更新记录不存在!");
        SaleChance temp = selectByPrimaryKey(id);
        AssertUtil.isTrue(null == temp, "待更新记录不存在!");
        temp.setDevResult(devResult);
        AssertUtil.isTrue(updateByPrimaryKeySelective(temp) < 1, "机会数据更新失败!");
    }
}
