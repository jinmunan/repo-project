package com.cy.store.controller;

import com.cy.common.utils.JsonResult;
import com.cy.store.entity.Product;
import com.cy.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Jinmunan
 * 2022/3/27
 * 21:10
 */
@Controller
@RequestMapping("/products")
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/hot_list")
    public JsonResult<List<Product>> getHotList() {
        List<Product> data = productService.findHotList();
        return new JsonResult<List<Product>>(OK, data);
    }
}
