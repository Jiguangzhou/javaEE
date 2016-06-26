package com.kaishengit.service;

import com.kaishengit.dao.MovieDao;
import com.kaishengit.entity.Movie;
import com.kaishengit.util.Page;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;
import java.util.UUID;

public class MovieService {

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
    private MovieDao movieDao = new MovieDao();

    public void updateFile(String title,Float rate,String releasyear,String sendtime,String daoyan,InputStream inputStream)throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(IOUtils.toByteArray(inputStream));

        Movie movie = movieDao.findMovieByTitle(title);
        if (title == null){

            String filename = saveFile(title,byteArrayInputStream);

            movie = new Movie();
            movie.setTitle(title);
            movie.setRate(rate);
            movie.setReleaseyear(releasyear);
            movie.setSendtime(sendtime);
            movie.setDaoyan(daoyan);
            movieDao.save(movie);
        }


    }
    private String saveFile(String fileName,InputStream inputStream) throws IOException {

        inputStream.reset();
        File dir = new File("E:/data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        String extName = fileName.substring(fileName.indexOf("."));
        String uuid = UUID.randomUUID().toString();
        fileName = uuid + extName;

        FileOutputStream output = new FileOutputStream(new File(dir,fileName));


        IOUtils.copy(inputStream, output);
       /* BufferedInputStream bufferInput = new BufferedInputStream(input);
        BufferedOutputStream bufferOutput = new BufferedOutputStream(output);

        byte[] buffer = new byte[1024];
        int len = -1;
        while((len = bufferInput.read(buffer))!=-1){
            bufferOutput.write(buffer,0,len);
        }**/
        output.flush();
        output.close();
        inputStream.close();

        return fileName;
    }


    public List<Movie> findAllDocument() {

        return movieDao.findAll();
    }

    public void updateFile(String fileName, long size, InputStream input) {

    }
}
