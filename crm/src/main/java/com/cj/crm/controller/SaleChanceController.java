package com.cj.crm.controller;

import com.cj.crm.common.base.BaseController;
import com.cj.crm.common.base.ResultInfo;
import com.cj.crm.common.utils.LoginUserUtil;
import com.cj.crm.entity.SaleChance;
import com.cj.crm.query.SaleChanceQuery;
import com.cj.crm.service.SaleChanceService;
import com.cj.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
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
            model.addAttribute("saleChance", saleChanceService.selectByPrimaryKey(id));
        }
        return "saleChance/add_update";
    }

    /**
     * 查询当前用户的开发计划
     *
     * @param saleChanceQuery
     * @param flag
     * @param request
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> querySaleChanceByParams(SaleChanceQuery saleChanceQuery, Integer flag, HttpServletRequest request) {
        //通过cookie拿到用户id
        int userId = LoginUserUtil.releaseUserIdFromCookie(request);
        //如果是营销机会管理,就显示全部的,如果是开发机会管理,就显示个人的
        if (null != flag && flag == 1) saleChanceQuery.setAssignMan(userId);
        return saleChanceService.querySaleChanceByParams(saleChanceQuery);
    }


    @RequestMapping(value = "/save")
    @ResponseBody
    public ResultInfo saveSaleChance(HttpServletRequest request, SaleChance saleChance) {
        int userId = LoginUserUtil.releaseUserIdFromCookie(request);
        //营销机会创建人为登录用户
        saleChance.setCreateMan(userService.selectByPrimaryKey(userId).getTrueName());
        saleChanceService.saveSaleChance(saleChance);
        return success("机会数据创建成功!");
    }


    @RequestMapping(value = "/update")
    @ResponseBody
    public ResultInfo saveSaleChance(SaleChance saleChance) {
        saleChanceService.updateSaleChance(saleChance);
        return success("机会数据更新成功!");
    }


    @RequestMapping(value = "/delete")
    @ResponseBody
    public ResultInfo deleteSaleChance(Integer[] ids) {
        saleChanceService.deleteSaleChance(ids);
        return success("机会数据删除成功!");
    }

    /**
     * 更新开发状态
     * @param id
     * @param devResult
     * @return
     */
    @RequestMapping("updateSaleChanceDevResult")
    @ResponseBody
    public ResultInfo updateSaleChanceDevResult(Integer id, Integer devResult) {
        saleChanceService.updateSaleChanceDevResult(id, devResult);
        return success("开发状态更新成功");
    }
}
