package com.kaishengit.test;

import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.junit.Test;

import java.util.List;

public class CriteriaTestCase {

    @Test
    public void testFindAll(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        List<User> userList = criteria.list();
        for (User user:userList){
            System.out.println(user);
        }
        session.getTransaction().commit();
    }

    @Test
    public void testFindByWhere(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);

        criteria.add(Restrictions.like("username","o", MatchMode.ANYWHERE));

        List<User> userList = criteria.list();
        for (User user:userList){
            System.out.println(user);
        }
        session.getTransaction().commit();
    }

    @Test
    public void testUnique(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);

        criteria.add(Restrictions.eq("password","456456"));

        User user = (User) criteria.uniqueResult();

        System.out.println(user);
        session.getTransaction().commit();
    }

    @Test
    public void testByPage(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);

        criteria.addOrder(Order.asc("id"));
        criteria.setFirstResult(0);
        criteria.setMaxResults(3);
        List<User> userList = criteria.list();
        for (User user:userList){
            System.out.println(user);
        }
        session.getTransaction().commit();
    }

    @Test
    public void testCount(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);

        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.rowCount());
        projectionList.add(Projections.max("id"));

        criteria.setProjection(projectionList);

        Object[] objects = (Object[]) criteria.uniqueResult();

        System.out.println("Count:"+objects[0]);
        System.out.println("Max:"+objects[1]);

        session.getTransaction().commit();
    }
}
