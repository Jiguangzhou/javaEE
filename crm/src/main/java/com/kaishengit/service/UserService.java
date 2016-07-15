package com.kaishengit.service;


import com.google.common.collect.Maps;
import com.kaishengit.mapper.RoleMapper;
import com.kaishengit.mapper.UserLogMapper;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import com.kaishengit.pojo.UserLog;
import com.kaishengit.util.ShiroUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
public class UserService {

    @Inject
    private RoleMapper roleMapper;
    @Inject
    private UserLogMapper userLogMapper;
    @Inject
    private UserMapper userMapper;

    /**
     * 创建用户登录日志
     * @param ip
     */
    public void saveUserLogin(String ip) {
        UserLog userLog = new UserLog();
        userLog.setLoginip(ip);
        userLog.setLogintime(DateTime.now().toString("yyyy-MM-dd HH:mm"));
        userLog.setUserid(ShiroUtil.getCurrentUserID());

        userLogMapper.save(userLog);
    }

    /**
     * 修改密码
     * @param password
     */
    public void changePassword(String password) {
        User user = ShiroUtil.getCurrentUser();
        user.setPassword(DigestUtils.md5Hex(password));
        userMapper.updateUser(user);
    }

    /**
     * 获取当前登录的用户日志
     * @param start
     * @param length
     * @return
     */
    public List<UserLog> findCurrentUserLog(String start, String length) {
        Map<String,Object> param = Maps.newHashMap();
        param.put("userId",ShiroUtil.getCurrentUserID());
        param.put("start",start);
        param.put("length",length);
        return userLogMapper.findByParam(param);
    }

    public Long findCurrentUserLogCount() {
        Map<String,Object> param = Maps.newHashMap();
        param.put("userId",ShiroUtil.getCurrentUserID());
        return userLogMapper.countByParam(param);
    }

    /**
     * 根据查询的参数获取用户的列表
     * @param param
     * @return
     */
    public List<User> findListByParam(Map<String,Object> param) {
        return userMapper.findByParam(param);
    }

    /**
     * 查询用户的总数量
     * @return
     */
    public Long findUserCount() {
        return userMapper.count();
    }

    /**
     * 根据查询的条件获得用户数量
     * @param param
     * @return
     */
    public Long findCountByParam(Map<String, Object> param) {
        return userMapper.countByParam(param);
    }

    /**
     * 查询所有的角色
     * @return
     */
    public List<Role> findAllRole() {
        return roleMapper.findAll();
    }

    /**
     * 添加新用户
     * @param user
     */
    @Transactional
    public void saveUser(User user) {
        user.setEnable(true);
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        //TODO 微信公众平台
        userMapper.save(user);
    }

    /**
     * 根据用户名查找
     * @param username
     * @return
     */
    public User findByUserName(String username) {
        return userMapper.findByUsername(username);
    }

    /**
     * 重置密码
     * @param id
     */
    public void resetUserPassword(Integer id) {
        User user = userMapper.findById(id);
        if (user != null){
            user.setPassword(DigestUtils.md5Hex("000000"));
            userMapper.updateUser(user);
        }
    }
    /**
     * 根据用户id查找用户
     * @param id
     * @return
     */
    public User findUserById(Integer id) {
        return userMapper.findById(id);
    }

    /**
     * 修改员工信息
     * @param user
     */
    public void editUser(User user) {
        userMapper.updateUser(user);
    }

    public List<User> findAllUser() {
        return userLogMapper.findAll();
    }
}
