package com.kaishengit.test;

import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

public class HibernateTestCase {

    @Test
    public void testSave(){
        Configuration configuration = new Configuration().configure();
//       //Hibernate 4.3
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
        //session
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        User user = new User();
        user.setUsername("李明");
        user.setAddress("China");
        user.setPassword("123123");

        session.save(user);


        transaction.commit();
    }

    @Test
    public void testUpdate(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class,11);
        user.setPassword("123456");

        session.getTransaction().commit();
    }

    @Test
    public void testDel(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class,11);

        session.delete(user);
        session.getTransaction().commit();
    }

    @Test
    public void testFindById(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class,8);
        System.out.println(user.getUsername());

        session.getTransaction().commit();
    }
}
