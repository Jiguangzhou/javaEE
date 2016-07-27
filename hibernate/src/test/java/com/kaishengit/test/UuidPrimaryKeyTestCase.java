package com.kaishengit.test;

import com.kaishengit.pojo.Task;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Cache;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.junit.Test;

public class UuidPrimaryKeyTestCase {


    @Test
    public void testSave(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Task task = new Task();
        task.setTitle("Today");
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

    @Test
    public void testVersion() throws InterruptedException {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Task task = (Task) session.get(Task.class,"40288191562bc5ad01562bc5b0010000");

        task.setTitle("Tomorrow");

        session.getTransaction().commit();
    }

    @Test
    public void testUpdate() throws InterruptedException {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Task task = (Task) session.get(Task.class,"40288191562b0b5201562b0b578e0000", LockOptions.UPGRADE);
        task.setTitle("World");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Session session1 = HibernateUtil.getSession();
                session1.beginTransaction();

                Task task1 = (Task) session1.get(Task.class,"40288191562b0b5201562b0b578e0000");
                task1.setTitle("lucky");

                session1.getTransaction().commit();
            }
        });

        thread.start();

        Thread.sleep(3000);

        session.getTransaction().commit();


    }


}
