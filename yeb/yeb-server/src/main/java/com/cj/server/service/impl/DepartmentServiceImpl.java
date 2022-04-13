package com.cj.server.service.impl;

import com.cj.server.mapper.DepartmentMapper;
import com.cj.server.pojo.Department;
import com.cj.server.service.IDepartmentService;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
