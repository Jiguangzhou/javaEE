package com.kaishengit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

public class JedisStringTestCase {


    private Jedis jedis = null;

    @Before
    public void setUp(){
        jedis = new Jedis("192.168.1.36");
    }

    @After
    public void close(){
        if (jedis != null){
            jedis.close();
        }
    }

    @Test
    public void testSet(){
        jedis.set("jedis","王五");
    }

    @Test
    public void testGet(){
        String value = jedis.get("jedis");
        System.out.println(value);
        Assert.assertEquals("王五",value);
    }

    @Test
    public  void testExists(){
        if (!jedis.exists("jedis")){
            jedis.set("jedis","version-2");
        }
    }

    @Test
    public void testIncr(){
        String key = "user";
        jedis.incr(key);
        System.out.println(jedis.get("user"));
    }

    @Test
    public void testIncrBy(){
        String key = "user";
        jedis.incrBy(key,20);
        System.out.println(jedis.get("user"));
    }

    @Test
    public void testDecr(){
        String key = "user";
        jedis.decr(key);
        System.out.println(jedis.get("user"));
    }

    @Test
    public void testIncrByFload(){
        String key = "user:money";
        System.out.println(jedis.get(key));
        jedis.incrByFloat(key,0.3F);
        System.out.println(jedis.get("user:money"));
    }

    @Test
    public void testAppend(){
        jedis.append("jedis","六");
        System.out.println(jedis.get("jedis"));
    }

    @Test
    public void testStrlen(){
        System.out.println(jedis.strlen("jedis"));
    }

    @Test
    public void testMset(){
        jedis.mset("k1","v1","k2","v2");
    }

    @Test
    public void testMget(){
        jedis.mget("k1","k2");
        System.out.println(jedis.mget("k1","k2"));
    }
}
