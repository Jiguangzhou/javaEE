package com.kaishengit.test;

import com.kaishengit.pojo.Task;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Cache;
import org.hibernate.Session;
import org.junit.Test;

public class UuidPrimaryKeyTestCase {


    @Test
    public void testSave(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Task task = new Task();
        task.setTitle("Hello,World");
        session.save(task);


        session.getTransaction().commit();
    }

    @Test
    public void testFindById(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Task task = (Task) session.get(Task.class,"40288191562b08ff01562b09058e0000");

//        System.out.println(task.getTitle());

        session.getTransaction().commit();

        //-------------------------------------------

        Cache cache = HibernateUtil.getSessionFactory().getCache();
        cache.evictEntityRegion(Task.class);

        Session session1 = HibernateUtil.getSession();
        session1.beginTransaction();

        Task task1 = (Task) session1.get(Task.class,"40288191562b08ff01562b09058e0000");
        System.out.println(task1.getTitle());

        session1.getTransaction().commit();

    }


}
