package com.cj.crm.controller;

import com.cj.common.base.BaseController;
import com.cj.common.base.ResultInfo;
import com.cj.common.utils.LoginUserUtil;
import com.cj.crm.entity.SaleChance;
import com.cj.crm.mapper.UserMapper;
import com.cj.crm.query.SaleChanceQuery;
import com.cj.crm.service.SaleChanceService;
import com.cj.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private UserService userService;

    //视图
    @RequestMapping(value = "/index")
    public String index() {
        return "saleChance/sale_chance";
    }

    //添加营销机会对话框
    @RequestMapping(value = "/addOrUpdateSaleChancePage")
    public String addSaleChancePage(Integer id, Model model) {
        if (null != id) {
            model.addAttribute("saleChance",saleChanceService.selectByPrimaryKey(id));
        }
        return "saleChance/add_update";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> querySaleChanceByParams(SaleChanceQuery saleChanceQuery) {
        return saleChanceService.querySaleChanceByParams(saleChanceQuery);
    }


    @RequestMapping(value = "/save")
    @ResponseBody
    public ResultInfo saveSaleChance(HttpServletRequest request, SaleChance saleChance) {
        int userId = LoginUserUtil.releaseUserIdFromCookie(request);
        //营销机会创建人为登录用户
        saleChance.setCreateMan(userService.findById(userId).getTruename());
        saleChanceService.saveSaleChance(saleChance);
        return success("机会数据创建成功!");
    }


    @RequestMapping(value = "/update")
    @ResponseBody
    public ResultInfo saveSaleChance(SaleChance saleChance) {
        saleChanceService.updateSaleChance(saleChance);
        return success("机会数据更新成功!");
    }


}
