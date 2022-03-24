package com.cy.store.service;

import com.cy.store.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * Created by Jinmunan
 * 2022/3/23
 * 10:34
 */
@SpringBootTest
class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @Test
    void addNewAddress() {
        for (int i = 0 ; i<2 ;i++){
            Address address = new Address();
            address.setUid(23);
            address.setPhone("123456789");
            address.setName("小痕");
            addressService.addNewAddress(address,23,"小鹤");
        }
    }

    @Test
    void setDefault(){
        addressService.setDefault(47,16,"钟楠");
    }
}