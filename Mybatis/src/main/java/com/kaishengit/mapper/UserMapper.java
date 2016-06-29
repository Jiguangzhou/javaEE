package com.kaishengit.mapper;

import com.kaishengit.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    User findById(Integer id);
    void save(User user);
    void update(User user);
    void del(Integer id);
    List<User> findAll();

    List<User> findByPage(@Param("start") int start, @Param("size") int size);
    List<User> findByQueryParam(Map<String,Object> queryParam);
    User findByParams(@Param("username") String username,@Param("password") String password);
    User findByMap(Map<String,Object> param);
}
