package com.cy.store.service;

import com.cy.store.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author THINKPAD
* @description 针对表【t_product】的数据库操作Service
* @createDate 2022-03-27 21:04:25
*/
public interface ProductService extends IService<Product> {
    /**
     * 查询热销商品的前四名
     * @return 热销商品前四名的集合
     */
    List<Product> findHotList();


    /**
     * 根据商品id查询商品详情
     * @param id 商品id
     * @return 匹配的商品详情，如果没有匹配的数据则返回null
     */
    Product findById(Integer id);
}
