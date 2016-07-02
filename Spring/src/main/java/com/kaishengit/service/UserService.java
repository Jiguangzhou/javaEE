package com.kaishengit.service;

import com.kaishengit.dao.LoginDao;
import com.kaishengit.dao.UserDao;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.Login;
import com.kaishengit.pojo.User;
import org.springframework.transaction.annotation.Transactional;


import javax.inject.Inject;
import javax.inject.Named;

@Named
@Transactional
public class UserService {

    @Inject
    private UserDao userDao;
    @Inject
    private LoginDao loginDao;
    @Inject
    private UserMapper userMapper;

    public void save(User user) {
        userMapper.save(user);
    }

    public User findById(Integer id){
        return userMapper.findById(id);
    }

    // 1.事务添加在service层
    // 2.碰到RunTimeException事务会回滚
    // 3.@Transactional(rollbackFor = Exception.class)这种碰到所有的异常都会回滚
    // 4.@Transactional(noRollbackFor = RunTimeException.class)这种碰到RuntimeException及其子异常都不会回滚
    // 5.@Transactional(Readonly = true)推荐查询方法为只读事务，性能较高
    // 6.@Transactional可以添加在类级别或者方法级别，添加在类级别的时候类中所有的方法都会添加事务
    // 7.@Transactional(isdation = Isdation.***)修改事务的隔离级别为***
    // 8.@Transactional(propagation = Propagation.***)修改传播属性为***

    public User login(String username, String password, String ip) {
        User user = userDao.findByUserName(username);
        if (user != null && user.getPassword().equals(password)) {
            loginDao.save(new Login(ip, user.getId()));
            return user;
        } else {
            throw new RuntimeException("账号或密码错误");
        }
    }

}
