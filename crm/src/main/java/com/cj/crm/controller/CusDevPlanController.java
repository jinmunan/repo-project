package com.cj.crm.controller;

import com.cj.crm.common.base.BaseController;
import com.cj.crm.common.base.ResultInfo;
import com.cj.crm.entity.CusDevPlan;
import com.cj.crm.query.CusDevPlanQuery;
import com.cj.crm.service.CusDevPlanService;
import com.cj.crm.service.SaleChanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Jinmunan
 * 2022/3/24
 * 16:33
 */
@Controller
@RequestMapping("cus_dev_plan")
public class CusDevPlanController extends BaseController {
    @Autowired
    private SaleChanceService saleChanceService;

    @Autowired
    private CusDevPlanService cusDevPlanService;

    @RequestMapping("index")
    public String index() {
        return "cusDevPlan/cus_dev_plan";
    }

    @RequestMapping("toCusDevPlanDataPage")
    public String toCusDevPlanDataPage(Integer sid, Model model) {
        model.addAttribute("saleChance", saleChanceService.selectByPrimaryKey(sid));
        return "cusDevPlan/cus_dev_plan_data";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryCusDevPlansByParams(CusDevPlanQuery cusDevPlanQuery) {
        return cusDevPlanService.queryCusDevPlansByParams(cusDevPlanQuery);
    }

    /**
     * 开发计划新增
     *
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public ResultInfo saveCusDevPlan(CusDevPlan cusDevPlan) {
        cusDevPlanService.saveCusDevPlan(cusDevPlan);
        return success("计划项添加成功!");
    }

    /**
     * 更新计划项
     *
     * @param cusDevPlan
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateCusDevPlan(CusDevPlan cusDevPlan) {
        cusDevPlanService.updateCusDevPlan(cusDevPlan);
        return success("计划项更新成功!");
    }

    /**
     * 删除计划项
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteCusDevPlan(Integer id) {
        cusDevPlanService.delCusDevPlan(id);
        return success("计划项删除成功!");
    }

    /**
     * 添加计划项页面
     *
     * @return
     */
    @RequestMapping("toAddOrUpdateCusDevPlanPage")
    public String addOrUpdateCusDevPlanPage(Integer sId, Integer id, Model model) {
        model.addAttribute("cusDevPlan", cusDevPlanService.selectByPrimaryKey(id));
        model.addAttribute("sId", sId);
        return "cusDevPlan/add_update";
    }
}
