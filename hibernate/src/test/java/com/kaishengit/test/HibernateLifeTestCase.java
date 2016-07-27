package com.kaishengit.test;

import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.max.MaxCore;

import java.util.List;

public class HibernateLifeTestCase {

    @Test
    public void testSave(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = new User();
        user.setUsername("李明");
        user.setAddress("China");
        user.setPassword("123123");

        session.save(user);
        System.out.println(user.getId());
        session.getTransaction().commit();
    }

    @Test
    public void testFindByGet(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class,7);

        session.getTransaction().commit();

        Assert.assertNull(user);
    }

    @Test
    public void testFindByLoad(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class,11);
        System.out.println(user.getUsername());

        session.getTransaction().commit();
    }

    @Test
    public void testUpdate(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class,11);

        session.getTransaction().commit();

        user.setUsername("Youyou");

        Session session1 = HibernateUtil.getSession();
        session1.beginTransaction();

        session1.update(user);
        session1.getTransaction().commit();
    }

    @Test
    public void testSaveOrUpdate(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = new User();
        user.setUsername("Mick");
        user.setAddress("AUS");
        user.setPassword("000000");

        session.saveOrUpdate(user);

        session.getTransaction().commit();

        user.setPassword("121212");

        Session session1 = HibernateUtil.getSession();
        session1.beginTransaction();

        session1.saveOrUpdate(user);

        session1.getTransaction().commit();
    }

    @Test
    public void testMerge(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class,11);
        session.getTransaction().commit();

        user.setPassword("000000");

        Session session1 = HibernateUtil.getSession();
        session1.beginTransaction();

        session1.merge(user);
        session1.getTransaction().commit();
    }

    @Test
    public void testDelete(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class,13);

        session.getTransaction().commit();

        Session session1 = HibernateUtil.getSession();
        session1.beginTransaction();

        session1.delete(user);

        session1.getTransaction().commit();
    }

    @Test
    public void testClear(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class,12);
        user.setPassword("111111");
        session.clear();

        session.getTransaction().commit();

    }

    @Test
    public void testFindByColumn(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "select id,username,password,address from User";
        Query query = session.createQuery(hql);
        List<Object[]> result = query.list();

        for (Object[] objects:result){
            System.out.println(objects[0]+"->"+objects[1]);
        }
        session.getTransaction().commit();
    }

    @Test
    public void testCount(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "select count(*),max(id) from User";
        Query query = session.createQuery(hql);
        Object[] objects = (Object[]) query.uniqueResult();
        System.out.println("Count:"+objects[0]);
        System.out.println("Max:"+objects[1]);

        session.getTransaction().commit();
    }

    @Test
    public void testPage(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from User order by id desc";
        Query query = session.createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(3);

        List<User> userList = query.list();
        for (User user:userList){
            System.out.println(user);
        }
        session.getTransaction().commit();
    }
}
