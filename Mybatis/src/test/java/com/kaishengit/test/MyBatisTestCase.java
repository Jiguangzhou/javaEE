package com.kaishengit.test;

import com.kaishengit.pojo.User;
import com.kaishengit.util.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;

public class MyBatisTestCase {

    private Logger logger = LoggerFactory.getLogger(MyBatisTestCase.class);

    @Test
    public void testMybatis(){
        try {
            //从classpath中读取mybatis配置文件
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            //根据SqlSessionFactoryBulider对象创建SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            //根据sqlSessionFactory创建SqlSession
            SqlSession sqlSession = sqlSessionFactory.openSession();

            //CRUD
            //str:namespace+id
            User user = sqlSession.selectOne("com.kaishengit.mapper.UserMapper.findById",2);
            logger.debug("{}",user);

            //关闭sqlSession
            sqlSession.close();

            Assert.assertNotNull(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testSave(){
        try {
            Reader reader = Resources.getResourceAsReader("mybatis.xml");

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

            SqlSession sqlSession = sqlSessionFactory.openSession();

            User user = new User();

            user.setUsername("John");
            user.setAddress("USA");
            user.setPassword("911911");

            sqlSession.insert("com.kaishengit.mapper.UserMapper.save",user);

            sqlSession.commit();
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        User user = sqlSession.selectOne("com.kaishengit.mapper.UserMapper.findById",4);
        user.setPassword("123456");
        sqlSession.update("com.kaishengit.mapper.UserMapper.update",user);

        sqlSession.commit();
        sqlSession.close();
    }


    public void testDel(){

    }
}
