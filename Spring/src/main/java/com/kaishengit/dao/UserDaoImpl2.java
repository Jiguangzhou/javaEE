package com.kaishengit.dao;

public class UserDaoImpl2 implements UserDao {
    @Override
    public Integer save() {
        System.out.println("UserDao add...");
        return 10;
    }
}
