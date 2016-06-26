package com.kaishengit.test;

import com.google.gson.Gson;
import com.kaishengit.entity.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GsonTestCase {

    @Test
    public void testToObject(){
        User user = new User(1,"Tom","USA",90.5F);
        //object --> {}
        Gson gson = new Gson();
        String json = gson.toJson(user);
        System.out.println(json);
    }

    @Test
    public void testMapObject(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",2);
        map.put("message","你有一封未读邮件");
        //map --> {}
        String json = new Gson().toJson(map);
        System.out.println(json);
    }

    @Test
    public void testArrayObject(){
        String[] address = {"China","USA","Canada","Australia","Russia"};
        //array --> []
        String json = new Gson().toJson(address);
        System.out.println(json);
    }

    @Test
    public void testObjectArray(){
        User[] users = new User[3];
        users[0] = new User(1,"Tom","USA",86.5F);
        users[1] = new User(2,"Jack","Canada",79.5F);
        users[2] = new User(3,"Lily","Austalia",90.5F);
        //Object array --> []
        String json = new Gson().toJson(users);
        System.out.println(json);
    }

    @Test
    public void testListToJson(){
        List<User> userList = new ArrayList<>();
        userList.add(new User(1,"Tom","USA",86.5F));
        userList.add(new User(2,"Jack","Canada",79.5F));
        userList.add(new User(3,"Lily","Austalia",90.5F));
        //list --> []
        String json = new Gson().toJson(userList);
        System.out.println(json);

    }
}
