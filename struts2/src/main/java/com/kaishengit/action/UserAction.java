package com.kaishengit.action;

import com.kaishengit.pojo.User;

import java.util.List;

public class UserAction {

    private User user;
    private List<String> names;


    public String toSave(){
        System.out.println("UserAction save...");
        return "success";
    }


}
