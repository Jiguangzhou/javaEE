package com.kaishengit.service;


import com.kaishengit.mapper.RoleMapper;
import com.kaishengit.mapper.UserLogMapper;
import com.kaishengit.mapper.UserMapper;

import javax.inject.Inject;

public class UserService {

    @Inject
    private RoleMapper roleMapper;
    @Inject
    private UserLogMapper userLogMapper;
    @Inject
    private UserMapper userMapper;
}
