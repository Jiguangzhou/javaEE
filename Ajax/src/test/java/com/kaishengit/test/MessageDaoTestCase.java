package com.kaishengit.test;

import com.kaishengit.dao.MessageDao;
import com.kaishengit.entity.Message;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.junit.Assert;
import org.junit.Test;

public class MessageDaoTestCase {

    private MessageDao messageDao = new MessageDao();

    @Test
    public void testfindById(){
        Message message = messageDao.findById(1);
        message = messageDao.findById(1);
        message = messageDao.findById(1);
        message = messageDao.findById(1);
        message = messageDao.findById(1);
        message = messageDao.findById(1);

        Assert.assertNotNull(message);
    }

    public void testEhcache(){
        CacheManager cacheManager = new CacheManager();
        Ehcache ehcache = cacheManager.getEhcache("mycache");

        //存缓存值
        Element element = new Element("user:1","Tom");
        ehcache.put(element);

        ehcache.remove("user:1");
        //取缓存值
        Element out = ehcache.get("user:1");
        String value = (String) out.getObjectValue();
        Assert.assertNotNull(value);
    }
}
