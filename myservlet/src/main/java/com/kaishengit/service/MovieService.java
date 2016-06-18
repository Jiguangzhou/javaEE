package com.kaishengit.service;

import com.kaishengit.dao.MovieDao;
import com.kaishengit.entity.Movie;
import com.kaishengit.util.Page;

import java.util.List;

public class MovieService {
    private MovieDao movieDao = new MovieDao();


    public List<Movie> findAllMovie() {
        return movieDao.findAll();

    }

    public Page<Movie> findMovieByPageNo(int pageNo) {

        int totalSize = movieDao.count().intValue();
        Page<Movie> page = new Page<>(10,pageNo,totalSize);

        List<Movie> movielist = movieDao.findByPage(page.getStart(),10);
        page.setItems(movielist);

        return page;
        /*int totalSize = movieDao.count().intValue();
        int size = 10;

        int totalPageSize = totalSize / size;
        if (totalSize % size != 0) {
            totalPageSize++;
        }
        if (pageNo>totalPageSize){
            pageNo = totalPageSize;
        }
        int start = (pageNo - 1) * size;
        return movieDao.findByPage(start, size);*/
    }

}
