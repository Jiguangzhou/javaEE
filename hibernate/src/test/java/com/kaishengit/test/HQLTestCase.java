package com.kaishengit.test;

import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

public class HQLTestCase {

    @Test
    public void testFindAll(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        //HQL全部是JAVA中的对象，与数据库无关
        String hql = "from User";
        Query query = session.createQuery(hql);
        List<User> userList = query.list();

        for (User user:userList){
            System.out.println(user);
        }
        session.getTransaction().commit();
    }

    @Test
    public void testFindByWhere(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        //引用占位符
        String hql = "from User as u where u.username = :username and u.address = :address ";
        Query query = session.createQuery(hql);
        query.setParameter("username","Mick");
        query.setParameter("address","China");

        List<User> userList = query.list();
        for (User user:userList){
            System.out.println(user);
        }
        session.getTransaction().commit();
    }

    @Test
    public void testFindUnique(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from User where password =:pwd";
        Query query = session.createQuery(hql);
        query.setParameter("pwd","456456");

        User user = (User) query.uniqueResult();
        System.out.println(user);
        session.getTransaction().commit();

    }
}
