package com.cy.store.service;

import com.cy.store.mapper.DictDistrictMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Jinmunan
 * 2022/3/23
 * 11:25
 */
@SpringBootTest
class DictDistrictServiceTest {

    @Autowired
    private DictDistrictMapper dictDistrictMapper;
    @Test
    void getByParent() {
        dictDistrictMapper.findByParent("86").forEach(System.out::println);
    }
}