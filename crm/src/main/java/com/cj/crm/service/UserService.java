package com.cj.crm.service;

import com.cj.crm.common.base.BaseService;
import com.cj.crm.common.utils.AssertUtil;
import com.cj.crm.common.utils.Md5Util;
import com.cj.crm.common.utils.PhoneUtil;
import com.cj.crm.common.utils.UserIDBase64;
import com.cj.crm.entity.SaleChance;
import com.cj.crm.entity.User;
import com.cj.crm.mapper.UserMapper;
import com.cj.crm.model.UserModel;
import com.cj.crm.query.SaleChanceQuery;
import com.cj.crm.query.UserQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author THINKPAD
 * @description 针对表【t_user】的数据库操作Service
 * @createDate 2022-03-21 10:25:05
 */
@Service
public class UserService extends BaseService<User, Integer> {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     * 1. 参数判断，判断用户姓名、用户密码非空
     * 如果参数为空，抛出异常（异常被控制层捕获并处理）
     * 2. 调用数据访问层，通过用户名查询用户记录，返回用户对象
     * 3. 判断用户对象是否为空
     * 如果对象为空，抛出异常（异常被控制层捕获并处理）
     * 4. 判断密码是否正确，比较客户端传递的用户密码与数据库中查询的用户对象中的用户密码
     * 如果密码不相等，抛出异常（异常被控制层捕获并处理）
     * 5. 如果密码正确，登录成功
     */
    public UserModel login(String username, String password) {
        // 1. 参数判断，判断用户姓名、用户密码非空
        checkLoginParams(username, password);
        // 2. 调用数据访问层，通过用户名查询用户记录，返回用户对象
        User user = userMapper.queryUserByName(username);
        // 3. 判断用户对象是否为空
        AssertUtil.isTrue(user == null, "用户姓名不存在！");
        // 4. 判断密码是否正确，比较客户端传递的用户密码与数据库中查询的用户对象中的用户密码
        String MD5Password = Md5Util.encode(password);
        AssertUtil.isTrue(!user.getUserPwd().equals(MD5Password), "密码错误！");
        // 返回构建用户对象
        return buildUserInfo(user);
    }


    /**
     * 校验参数是否为空
     *
     * @param userName
     * @param userPwd
     */
    private void checkLoginParams(String userName, String userPwd) {
        // 验证用户姓名
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户姓名不能为空！");
        // 验证用户密码
        AssertUtil.isTrue(StringUtils.isBlank(userPwd), "用户密码不能为空！");
    }


    /**
     * 绑定传输对象
     *
     * @param user
     * @return
     */
    private UserModel buildUserInfo(User user) {
        UserModel userModel = new UserModel();
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userModel.setUsername(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        return userModel;
    }


    /**
     * 修改密码
     *
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUserPassword(Integer userId, String oldPassword, String newPassword, String confirmPassword) {
        //验证
        checkParams(userId, oldPassword, newPassword, confirmPassword);
        //设置实体类
        User user = userMapper.selectByPrimaryKey(userId);
        //设置实体类
        user.setUserPwd(Md5Util.encode(newPassword));
        //更新密码
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) < 1, "修改密码失败!");
    }


    /**
     * 查询所有的销售人员
     *
     * @return
     */
    public List<Map<String, Object>> queryAllSales() {
        return userMapper.queryAllSales();
    }

    /**
     * 校验密码
     *
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    private void checkParams(Integer userId, String oldPassword, String newPassword, String confirmPassword) {
        User user = userMapper.selectByPrimaryKey(userId);
        AssertUtil.isTrue(userId == null || null == user, "用户未登录或不存在");
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword), "请输入原始密码");
        AssertUtil.isTrue(StringUtils.isBlank(newPassword), "请输入新密码");
        AssertUtil.isTrue(StringUtils.isBlank(confirmPassword), "请输入确认密码");
        AssertUtil.isTrue(!user.getUserPwd().equals(Md5Util.encode(oldPassword)), "原始密码不正确");
        AssertUtil.isTrue(!newPassword.equals(confirmPassword), "密码必须保存一致");
        AssertUtil.isTrue(user.getUserPwd().equals(Md5Util.encode(newPassword)), "与原始密码一致");
    }

    /**
     * 用户模糊查询
     *
     * @param userQuery
     * @return
     */
    public Map<String, Object> queryUserByParams(UserQuery userQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(userQuery.getPage(), userQuery.getLimit());
        PageInfo<User> info = new PageInfo<>(userMapper.selectByParams(userQuery));
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", info.getTotal()); //count
        map.put("data", info.getList());
        return map;
    }

    /**
     * 用户添加
     */
    public void saveUser(User user) {
        /**
         * 参数校验
         * 默认参数
         * 默认密码设置
         * */
        checkFormParams(user.getUserName(), user.getEmail(), user.getPhone());
        AssertUtil.isTrue(null != userMapper.queryUserByName(user.getUserName()), "用户名不能重复");
        user.setIsValid(1);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        user.setUserPwd(Md5Util.encode("123456"));
        AssertUtil.isTrue(insertSelective(user) < 1, "用户添加失败");
    }

    private void checkFormParams(String userName, String email, String phone) {
        AssertUtil.isTrue(StringUtils.isBlank(userName), "请输入用户名");
        AssertUtil.isTrue(StringUtils.isBlank(email), "请输入邮箱");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone), "手机号不合法");
    }

    /**
     * 用户更新
     */
    public void updateUser(User user) {
        /**
         * 参数校验
         * id记录存在
         * 默认参数
         * 默认密码设置
         * */
        AssertUtil.isTrue(null == selectByPrimaryKey(user.getId()), "用户记录不存在");
        checkFormParams(user.getUserName(), user.getEmail(), user.getPhone());
        User temp = userMapper.queryUserByName(user.getUserName());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(user.getId())), "用户已存在");
        user.setUpdateDate(new Date());
        AssertUtil.isTrue(updateByPrimaryKeySelective(user) < 1, "用户更新失败");
    }

    /**
     * 用户删除
     */
    public void deleteUserByIds(Integer[] ids) {
        AssertUtil.isTrue(null == ids || ids.length == 0, "请选择待删除的数据");
        Integer rows = userMapper.deleteBatch(ids);
        AssertUtil.isTrue(rows != ids.length, "记录删除失败");
    }


}
