package com.cj.acl.service.impl;

import com.cj.acl.entity.User;
import com.cj.acl.service.PermissionService;
import com.cj.acl.service.UserService;
import com.cj.security.entity.SecurityUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jinmunan
 * 2022/4/13
 * 14:37
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Autowired
    private PermissionService permissionService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询数据
        User user = userService.selectByUsername(username);
        //判断
        if (null == user) {
            throw new UsernameNotFoundException("用户不存在");
        }
        User curUser = new User();
        BeanUtils.copyProperties(user, curUser);

        //根据用户名查询用户权限列表信息
        List<String> permissionValueList = permissionService.selectPermissionValueByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser();
        securityUser.setPermissionValueList(permissionValueList);
        return securityUser;
    }
}
