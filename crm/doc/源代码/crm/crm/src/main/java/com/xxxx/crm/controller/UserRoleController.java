package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.service.UserRoleService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * 乐字节：专注线上IT培训
 * 答疑老师微信：lezijie
 */
@Controller
public class UserRoleController extends BaseController {

    @Resource
    private UserRoleService userRoleService;
}
