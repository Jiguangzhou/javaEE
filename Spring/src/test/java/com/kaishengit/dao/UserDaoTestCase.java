package com.kaishengit.dao;

import com.kaishengit.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class UserDaoTestCase implements Serializable {

    @Inject
    private UserDao userDao;

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("Lily");
        user.setAddress("China");
        user.setPassword("123123");

        userDao.save(user);
    }

    @Test
    public void testUpdate(){
        User user = userDao.findById(9);
        user.setUsername("Xiaoming");
        user.setPassword("000123");

        userDao.update(user);

    }

    @Test
    public void testDel(){
        userDao.del(6);

    }

    @Test
    public void testFindById(){
        User user = userDao.findById(8);
        Assert.assertNotNull(user);
        System.out.println(user);
    }

    @Test
    public void testFindByUserName(){
        User user = userDao.findByUserName("Lily");
        Assert.assertNotNull(user);
    }

    @Test
    public void testFindAll(){
        List<User> userList = userDao.findAll();
        Assert.assertEquals(userList.size(),4);
        for(User user:userList){
            System.out.println(user);
        }
    }

    @Test
    public void testCount(){
        Long count = userDao.count();
        Assert.assertEquals(count.intValue(),4);
    }
}
