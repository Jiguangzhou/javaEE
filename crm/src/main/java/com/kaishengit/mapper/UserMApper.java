package com.kaishengit.mapper;

import com.kaishengit.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    User findByUsername(String username);

    void updateUser(User user);

    void save(User user);

    List<User> findByParam(Map<String, Object> param);

    Long count();

    Long countByParam(Map<String, Object> param);


    User findById(Integer id);
}
