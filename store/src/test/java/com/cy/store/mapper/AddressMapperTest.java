package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Jinmunan
 * 2022/3/23
 * 10:13
 */
@SpringBootTest
class AddressMapperTest {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    void insertSelective() {
        Address address = new Address();
        address.setUid(23);
        address.setPhone("123456789");
        address.setName("小痕");
        this.addressMapper.insertSelective(address);
    }

    @Test
    void countByUid() {
        System.out.println(addressMapper.countByUid(23));
    }

    @Test
    void findByUid(){
        addressMapper.findByUid(23).forEach(System.out::println);
    }

    @Test
    public void deleteByAid() {
        Integer aid = 4;
        Integer rows = addressMapper.deleteByAid(aid);
        System.out.println("rows=" + rows);
    }

    @Test
    public void findLastModified() {
        Integer uid = 23;
        Address result = addressMapper.findLastModified(uid);
        System.out.println(result);
    }
}