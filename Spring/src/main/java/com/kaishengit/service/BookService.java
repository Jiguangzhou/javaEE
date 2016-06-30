package com.kaishengit.service;

import com.kaishengit.dao.UserDao;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class BookService {

    private String bookName;
    private Integer num;
    private List<String> lists;
    private Set<String> sets;
    private Map<String,Object> maps;
    private Properties properties;
    private UserDao userDao;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public List<String> getLists() {
        return lists;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }

    public Set<String> getSets() {
        return sets;
    }

    public void setSets(Set<String> sets) {
        this.sets = sets;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void bookList() {
        System.out.println(
                "BookService{" +
                        "bookName='" + bookName + '\'' +
                        ", num=" + num +
                        ", lists=" + lists +
                        ", sets=" + sets +
                        ", maps=" + maps +
                        ", properties=" + properties +
                        '}');
    }
}
