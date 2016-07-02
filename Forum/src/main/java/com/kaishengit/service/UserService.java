package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
//
    private Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserDao userDao = new UserDao();

    /**
     * @param username 账户
     * @param password 密码
     * @return 返回值 成功是用户user，失败是null
     */
    public User login(final String username, String password) {

        final User user = userDao.findByUsername(username);
        //password = DigestUtils.md5Hex(password+SALT);
        if (user != null && user.getPassword().equals(password)) {
            logger.debug("{}登录系统",username);
            return user;
        }
        return null;
        }

    }

