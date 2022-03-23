package com.cy.store.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Jinmunan
 * 2022/3/23
 * 11:20
 */
@SpringBootTest
class DictDistrictMapperTest {

    @Autowired
    private DictDistrictMapper dictDistrictMapper;

    @Test
    void findByParent() {
        dictDistrictMapper.findByParent("110100").forEach(System.out::println);
    }

    @Test
    void findNameByCode(){
        String nameByCode = dictDistrictMapper.findNameByCode("610000");
        System.out.println(nameByCode);
    }
}