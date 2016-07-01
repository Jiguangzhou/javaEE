package com.kaishengit.test;

import com.kaishengit.service.BookService;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleDemoTestCase {

    @Test
    public void testUserDao() {
        //工厂模式
        //在默认情况下，spring容器中管理的类会被写成单例类,类的对象会在容器启动时被创建
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");


        /*BookService bookService = (BookService) context.getBean("BookService");
        bookService.bookList();*/

        UserService userService = (UserService) context.getBean("UserService");

        /*userService.sayHi();*/
       /* UserDao userDao = (UserDao) context.getBean("userDao");

        userDao.sayHello();

        UserDao userDao1 = (UserDao) context.getBean("userDao");
        System.out.println(userDao == userDao1);*/
    }
}
