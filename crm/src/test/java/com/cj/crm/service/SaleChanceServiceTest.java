package com.cj.crm.service;

import com.cj.crm.query.SaleChanceQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Jinmunan
 * 2022/3/22
 * 9:32
 */
@SpringBootTest
class SaleChanceServiceTest {
    @Autowired
    private SaleChanceService saleChanceService;

    @Test
    void querySaleChanceByParams() {
        SaleChanceQuery saleChanceQuery = new SaleChanceQuery();
        Map<String, Object> map = saleChanceService.querySaleChanceByParams(saleChanceQuery);
        System.out.println(map);
    }
}