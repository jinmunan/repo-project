package com.cy.store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.store.entity.Address;

/**
 * @author THINKPAD
 * @description 针对表【t_address】的数据库操作Service
 * @createDate 2022-03-23 09:57:46
 */
public interface AddressService extends IService<Address> {
    void addNewAddress(Address address, Integer uid, String username);
}
