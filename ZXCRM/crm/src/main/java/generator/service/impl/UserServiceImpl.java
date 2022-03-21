package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.entity.User;
import generator.service.UserService;
import generator.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author THINKPAD
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2022-03-21 10:55:04
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




