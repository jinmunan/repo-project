package com.cj.crm.controller;

import com.cj.common.base.BaseController;
import com.cj.crm.query.SaleChanceQuery;
import com.cj.crm.service.SaleChanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Jinmunan
 * 2022/3/22
 * 9:13
 */
@SuppressWarnings("all")
@Controller
@RequestMapping(value = "/sale_chance")
public class SaleChanceController extends BaseController {



    @Autowired
    private SaleChanceService saleChanceService;

    //视图
    @RequestMapping(value = "/index")
    public String index(){
        return "saleChance/sale_chance";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> querySaleChanceByParams(SaleChanceQuery saleChanceQuery) {
        System.out.println(saleChanceQuery);
        return saleChanceService.querySaleChanceByParams(saleChanceQuery);
    }
}
