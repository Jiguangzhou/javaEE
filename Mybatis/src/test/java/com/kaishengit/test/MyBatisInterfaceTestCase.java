package com.kaishengit.test;

import com.google.common.collect.Maps;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.User;
import com.kaishengit.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class MyBatisInterfaceTestCase {

    private Logger logger = LoggerFactory.getLogger(MyBatisInterfaceTestCase.class);

    @Test
    public void testfindById(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findById(6);
        logger.debug("{}",user);

        sqlSession.close();
        Assert.assertNotNull(user);
    }

    @Test
    public void testSave(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("Xiaoming");
        user.setAddress("China");
        user.setPassword("110110");

        userMapper.save(user);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdate(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.findById(7);
        user.setPassword("000000");

        userMapper.update(user);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDel(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        userMapper.del(6);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testfindAll(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> userList = userMapper.findAll();
        for (User user:userList){
            logger.debug("{}",user);
        }
        sqlSession.close();
    }

    @Test
    public void testfindByParams(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.findByParams("Jack","456456");
        logger.debug("{}",user);

        sqlSession.close();

        Assert.assertNotNull(user);

    }

    @Test
    public void testfindByMap(){
        SqlSession sqlSission = MyBatisUtil.getSqlSession();
        UserMapper userMapper = sqlSission.getMapper(UserMapper.class);
        Map<String,Object> param = Maps.newHashMap();
        param.put("username","Xiaoming");
        param.put("password","000000");

        User user = userMapper.findByMap(param);
        logger.debug("{}",user);

        sqlSission.close();


    }

    @Test
    public void testfindByQueryParam(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Map<String,Object> queryparam = Maps.newHashMap();
        queryparam.put("username","John");
        queryparam.put("address","USA");
        queryparam.put("password","911911");
        userMapper.findByQueryParam(queryparam);

        sqlSession.close();
    }

    @Test
    public void testfindByPage(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findByPage("0","2");
        for (User user:userList){
            logger.debug("{}",user);
        }
        sqlSession.close();
    }
}
