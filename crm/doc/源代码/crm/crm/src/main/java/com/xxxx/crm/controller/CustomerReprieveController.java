package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.query.CustomerReprieveQuery;
import com.xxxx.crm.service.CustomerReprieveService;
import com.xxxx.crm.vo.CustomerReprieve;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 乐字节：专注线上IT培训
 * 答疑老师微信：lezijie
 */
@RequestMapping("customer_rep")
@Controller
public class CustomerReprieveController extends BaseController {

    @Resource
    private CustomerReprieveService customerReprieveService;


    /**
     * 分页条件查询流失客户暂缓操作的列表
     *
     *
     * 乐字节：专注线上IT培训
     * 答疑老师微信：lezijie
     * @param customerReprieveQuery
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryCustomerReprieveByParams(CustomerReprieveQuery customerReprieveQuery) {
        return customerReprieveService.queryCustomerReprieveByParams(customerReprieveQuery);
    }

    /**
     * 添加暂缓数据
     *
     *
     * 乐字节：专注线上IT培训
     * 答疑老师微信：lezijie
     * @param customerReprieve
     * @return com.xxxx.crm.base.ResultInfo
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addCustomerRepr(CustomerReprieve customerReprieve) {
        customerReprieveService.addCustomerRepr(customerReprieve);
        return success("添加暂缓数据成功！");
    }


    /**
     * 修改暂缓数据
     *
     *
     * 乐字节：专注线上IT培训
     * 答疑老师微信：lezijie
     * @param customerReprieve
     * @return com.xxxx.crm.base.ResultInfo
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateCustomerRepr(CustomerReprieve customerReprieve) {
        customerReprieveService.updateCustomerRepr(customerReprieve);
        return success("修改暂缓数据成功！");
    }


    /**
     * 打开添加/修改暂缓数据的页面
     *
     *
     * 乐字节：专注线上IT培训
     * 答疑老师微信：lezijie
     * @param
     * @return java.lang.String
     */
    @RequestMapping("toAddOrUpdateCustomerReprPage")
    public String toAddOrUpdateCustomerReprPage(Integer lossId, HttpServletRequest request, Integer id) {
        // 将流失客户ID存到作用域中
        request.setAttribute("lossId", lossId);

        // 判断ID是否为空
        if (id != null) {
            // 通过主键ID查询暂缓数据
            CustomerReprieve customerRep = customerReprieveService.selectByPrimaryKey(id);
            // 设置到请求域中
            request.setAttribute("customerRep", customerRep);
        }

        return "customerLoss/customer_rep_add_update";
    }


    /**
     * 删除暂缓数据
     *
     *
     * 乐字节：专注线上IT培训
     * 答疑老师微信：lezijie
     * @param id
     * @return com.xxxx.crm.base.ResultInfo
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo updateCustomerRepr(Integer id) {
        customerReprieveService.deleteCustomerRepr(id);
        return success("删除暂缓数据成功！");
    }
}
