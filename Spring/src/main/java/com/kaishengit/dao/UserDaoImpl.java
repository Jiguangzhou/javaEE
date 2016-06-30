package com.kaishengit.dao;

public class UserDaoImpl implements UserDao {
    @Override
    public Integer save() {
        System.out.println("UserDao save...");

        if (1 == 1) {
            throw new RuntimeException("读取异常");
        }
        return 100;
    }
}
