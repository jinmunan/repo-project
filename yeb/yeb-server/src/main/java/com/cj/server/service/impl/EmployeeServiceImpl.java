package com.cj.server.service.impl;

import com.cj.server.mapper.EmployeeMapper;
import com.cj.server.pojo.Employee;
import com.cj.server.service.IEmployeeService;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
