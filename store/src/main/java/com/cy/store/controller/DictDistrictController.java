package com.cy.store.controller;

import com.cy.common.utils.JsonResult;
import com.cy.store.entity.DictDistrict;
import com.cy.store.service.DictDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Jinmunan
 * 2022/3/23
 * 11:30
 */
@Controller
@RequestMapping(value = "/dictDistrict")
public class DictDistrictController extends BaseController {
    @Autowired
    private DictDistrictService dictDistrictService;

    @RequestMapping(value = {"/", ""}) //表示拦截所有的
    @ResponseBody
    public JsonResult<List<DictDistrict>> getByParent(String parent) {
        List<DictDistrict> list = dictDistrictService.getByParent(parent);
        return new JsonResult<>(OK, list);
    }
}
