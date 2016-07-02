package com.kaishengit.service;


import com.kaishengit.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class UserServiceTestCase {

    @Inject
    private UserService userService;

    @Test
    public void testLogin(){
        userService.login("Jack","456456","10.3.4.6");
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("S+M");
        user.setAddress("USA");
        user.setPassword("1111111");

        userService.save(user);
    }

    @Test
    public void testFindById(){
        User user = userService.findById(10);
        Assert.assertNotNull(user);
    }
}
