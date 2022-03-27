package com.cy.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.store.entity.ProductCategory;
import com.cy.store.service.ProductCategoryService;
import com.cy.store.mapper.ProductCategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author THINKPAD
* @description 针对表【t_product_category】的数据库操作Service实现
* @createDate 2022-03-27 21:05:14
*/
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory>
    implements ProductCategoryService{

}




