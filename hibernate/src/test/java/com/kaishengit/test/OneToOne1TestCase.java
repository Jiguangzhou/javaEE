package com.kaishengit.test;

import com.kaishengit.pojo.Topic;
import com.kaishengit.pojo.TopicContent;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

public class OneToOne1TestCase {

    @Test
    public void testSave(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        TopicContent topicContent = new TopicContent();
        topicContent.setContent("Luckly");

        Topic topic = new Topic();
        topic.setTitle("Topic-2");
        topic.setTopicContent(topicContent);

        session.save(topicContent);
        session.save(topic);


        session.getTransaction().commit();
    }


    @Test
    public void testFind(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Topic topic = (Topic) session.get(Topic.class,2);
        System.out.println(topic.getTitle());
        System.out.println(topic.getTopicContent().getContent());


        session.getTransaction().commit();
    }
}
