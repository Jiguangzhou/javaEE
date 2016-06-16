package com.kaishengit.test;

import com.kaishengit.entity.User;
import com.kaishengit.service.UserService;
import org.junit.Assert;
import org.junit.Test;

public class UserServiceTestCase {

    private UserService userService = new UserService();

    @Test
    public void testLogin() {
        User user = userService.login("Tom","123123");
        Assert.assertNotNull(user);
    }
}