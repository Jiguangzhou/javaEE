package com.kaishengit.dao;

import com.kaishengit.pojo.Login;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class LoginDaoTestCase {

    @Inject
    private LoginDao loginDao;

    @Test
    public void testSave(){
        Login login = new Login("1.2.3.4",1);
        loginDao.save(login);

    }

}
