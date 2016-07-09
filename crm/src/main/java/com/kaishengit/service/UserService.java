package com.kaishengit.service;


import com.kaishengit.mapper.RoleMapper;
import com.kaishengit.mapper.UserLogMapper;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.User;
import com.kaishengit.pojo.UserLog;
import com.kaishengit.util.ShiroUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.inject.Named;

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
    public void saveUserLog(String ip){
        UserLog userLog = new UserLog();
        userLog.setLogintime(DateTime.now().toString("yyyy-mm-dd HH:mm"));
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
}
