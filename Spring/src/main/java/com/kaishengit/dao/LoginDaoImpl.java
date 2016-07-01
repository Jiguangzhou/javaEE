package com.kaishengit.dao;

import com.kaishengit.pojo.Login;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class LoginDaoImpl implements LoginDao {

    @Inject
    private JdbcTemplate jdbcTemplate;
    @Override
    public void save(Login login) {
        String sql = "insert into t_login(ip,userid) values(?,?)";
        jdbcTemplate.update(sql,login.getIp(),login.getUserid());
    }
}
