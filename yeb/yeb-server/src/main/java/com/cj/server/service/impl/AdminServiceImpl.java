package com.cj.server.service.impl;

import com.cj.server.pojo.Admin;
import com.cj.server.mapper.AdminMapper;
import com.cj.server.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jinmunan
 * @since 2022-04-11
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
