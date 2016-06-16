package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import com.kaishengit.util.EmailUtil;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {

    private final String SALT = "@$!%!^&!^&!^&@&*";
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
            //新建子线程
            //给成功登陆的用户发一封邮件
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    EmailUtil.sendHtmlEmail(user.getAddress(), "登录提示", "你的账号" + username + "在" + DateTime.now().toString("yyyy-MM-dd HH:mm:ss") + "登录了系统");

                }
            });

            thread.start();

            return user;
        }
        return null;
    }
}
