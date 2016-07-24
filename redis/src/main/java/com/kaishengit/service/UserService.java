package com.kaishengit.service;

import com.google.gson.Gson;
import com.kaishengit.pojo.User;
import io.protostuff.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class UserService {

    @Autowired
    private JedisPool jedisPool;

    public Jedis getJedis(){
        return jedisPool.getResource();
    }

    public void saveUser(User user){
        Schema<User> userSchema = RuntimeSchema
    }

    public User findByUserId(Integer userid){
        Jedis jedis = getJedis();
        String json = jedis.get(KeyUtil.userKey(userid));
        User user = new Gson().fromJson(json,User.class);
        if (user == null){
        }
        jedis.close();
        return user;
    }

//    private RedisTemplate<String,User> redisTemplate;
//    @Autowired
//    public UserService(RedisTemplate<String,User> redisTemplate){
//        this.redisTemplate = redisTemplate;
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
//    }
//
//    public User findByUserId(Integer userid){
//        User user = redisTemplate.opsForValue().get(KeyUtil.userKey(userid));
//        if (user == null){
//
//        }
//        return user;
//    }
}
