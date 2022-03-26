package com.cj.crm.controller;

import com.cj.common.base.BaseController;
import com.cj.crm.mapper.SaleChanceMapper;
import com.cj.crm.service.SaleChanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("index")
    public String index() {
        return "cusDevPlan/cus_dev_plan";
    }

    @RequestMapping("toCusDevPlanDataPage")
    public String toCusDevPlanDataPage(Integer id, Model model) {
        model.addAttribute("saleChance", saleChanceService.selectByPrimaryKey(id));
        return "cusDevPlan/cus_dev_plan_data";
    }

}
