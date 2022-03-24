package com.cy.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.AddressService;
import com.cy.store.service.DictDistrictService;
import com.cy.store.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author THINKPAD
 * @description 针对表【t_address】的数据库操作Service实现
 * @createDate 2022-03-23 09:57:46
 */
@SuppressWarnings("all")
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private DictDistrictService dictDistrictService;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    //添加地址
    @Override
    public void addNewAddress(Address address, Integer uid, String username) {
        int count = addressMapper.countByUid(uid);
        if (count >= maxCount) throw new AddressCountLimitException("收货地址超出上限");

        //依赖于DictDistrictService接口
        //获得省市区名字
        String province = dictDistrictService.getNameByCode(address.getProvinceCode());
        String city = dictDistrictService.getNameByCode(address.getCityCode());
        String area = dictDistrictService.getNameByCode(address.getAreaCode());
        address.setProvinceName(province);
        address.setCityName(city);
        address.setAreaName(area);

        //补全4项日志
        address.setUid(uid);
        Integer isDefault = count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());
        address.setModifiedUser(username);
        address.setCreatedUser(username);
        int row = addressMapper.insertSelective(address);
        if (row < 1) throw new InsertException("插入用户的收获地址产生未知的异常");
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        return addressMapper.findByUid(uid);
    }

    /**
     * 设置默认值
     *
     * @param aid
     * @param uid
     * @param modifiedUser
     * @return
     */
    @Override
    public void setDefault(Integer aid, Integer uid, String modifiedUser) {
        //查询记录是否存在
        Address address = addressMapper.findByAid(aid);
        if (null == address) throw new AddressNotFoundException("收获地址不存在");
        //检测当前获取到的收获地址数据的归属
        if (!address.getUid().equals(uid)) throw new AccessDeniedException("非法访问数据");
        //设置所有地址为非默认
        int rows = addressMapper.updateIsDefaultByUid(uid);
        if (rows < 1) throw new UpdateException("更新数据产生未知的异常");
        //设置默认地址
        if (addressMapper.updateIsDefaultByAid(aid, modifiedUser, new Date()) < 1)
            throw new UpdateException("更新数据产生未知的异常");
    }

//    @Override
//    public void deleteAddress(Integer uid, Integer aid, String username) {
//        // 根据参数aid，调用findByAid()查询收货地址数据  网页缓存   数据库没有数据
//        Address result = addressMapper.findByAid(aid);
//        // 判断查询结果是否为null
//        if (result == null) throw new AddressNotFoundException("尝试访问的收货地址数据不存在");
//        // 判断查询结果中的uid与参数uid是否不一致(使用equals()判断)
//        if (!result.getUid().equals(uid)) throw new AccessDeniedException("非法访问");
//
//        // 根据参数aid，调用deleteByAid()执行删除
//        Integer rows1 = addressMapper.deleteByAid(aid);
//        if (rows1 != 1) throw new DeleteException("删除收货地址数据时出现未知错误，请联系系统管理员");
//
//        // 判断查询结果中的isDefault是否为0
//        if (result.getIsDefault() == 0) {
//            return;
//        }
//
//        // 调用持久层的countByUid()统计目前还有多少收货地址
//        Integer count = addressMapper.countByUid(uid);
//        // 判断目前的收货地址的数量是否为0
//        if (count == 0) {
//            return;
//        }
//
//        // 调用findLastModified()找出用户最近修改的收货地址数据
//        Address lastModified = addressMapper.findLastModified(uid);
//        // 从以上查询结果中找出aid属性值
//        Integer lastModifiedAid = lastModified.getAid();
//        // 调用持久层的updateDefaultByAid()方法执行设置默认收货地址，并获取返回的受影响的行数
//        Integer rows2 = addressMapper.updateDefaultByAid(lastModifiedAid, username, new Date());
//        // 判断受影响的行数是否不为1
//        if (rows2 != 1) {
//            // 是：抛出UpdateException
//            throw new UpdateException("更新收货地址数据时出现未知错误，请联系系统管理员");
//        }
//    }



}




