package com.cy.store.controller;

import com.cy.common.utils.JsonResult;
import com.cy.store.entity.Address;
import com.cy.store.service.AddressService;
import jdk.nashorn.internal.scripts.JO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Jinmunan
 * 2022/3/23
 * 10:44
 */
@Slf4j
@Controller
@RequestMapping(value = "/address")
public class AddressController extends BaseController {

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/add_new_address")
    @ResponseBody
    public JsonResult<Void> addNewAddress(Address address, HttpSession httpSession) {
        log.info("获取的地址信息是:" + address.toString());
        Integer uid = getUidFromSession(httpSession);
        String username = getUsernameFromSession(httpSession);
        addressService.addNewAddress(address, uid, username);
        return new JsonResult<>(OK);
    }

    @RequestMapping(value = "/addresses")
    @ResponseBody
    public JsonResult<List<Address>> getAddress(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<Address> list = addressService.getByUid(uid);
        return new JsonResult<>(OK, list);
    }

    @RequestMapping(value = "/{aid}/set_default")
    @ResponseBody
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.setDefault(aid, uid, username);
        return new JsonResult<>(OK);
    }

    @RequestMapping(value = "/{aid}/delete")
    @ResponseBody
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.delete(aid, uid, username);
        return new JsonResult<>(OK);
    }

}
