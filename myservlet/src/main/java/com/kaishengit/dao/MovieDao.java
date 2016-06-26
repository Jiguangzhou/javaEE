package com.kaishengit.dao;

import com.kaishengit.entity.Movie;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

public class MovieDao {

    public List<Movie> findAll(){
        String sql = "select * from movie";
        return DbHelp.query(sql,new BeanListHandler<>(Movie.class));
    }

    public List<Movie> findByPage(int start,int size){
        String sql = "select * from movie limit ?,?";
        return DbHelp.query(sql,new BeanListHandler<>(Movie.class),start,size);

    }
    public void save(Movie movie){
        String sql = "INSERT INTO douban_movie(title,rate,releaseyear,sendtime,daoyan,jianjie) values(?,?,?,?,?,?)";
        DbHelp.update(sql,movie.getTitle(),movie.getRate(),movie.getReleaseyear(),movie.getSendtime(),movie.getDaoyan(),movie.getJianjie());
    }

    public Movie findMovieByTitle(String title) {
        String sql = "select * from douban_movie where title = ?";

        return DbHelp.query(sql,new BeanHandler<>(Movie.class),title);
    }

    public Long count(){
        String sql = "select count(*) from movie";
        return DbHelp.query(sql,new ScalarHandler<Long>());
    }
}
