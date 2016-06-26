package com.kaishengit.web;

import com.kaishengit.dao.MovieDao;
import com.kaishengit.entity.Movie;
import com.kaishengit.service.MovieService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/preview")
public class PreviewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("file");
        String down = req.getParameter("down");
        if (StringUtils.isNotEmpty(title)) {
            Movie movie = new MovieDao().findMovieByTitle(title);
            if (title == null) {
                resp.sendError(404);
            } else {
                String saveFileName = movie.getTitle();
                File file = new File("E:/data", saveFileName);
                if (file.exists()) {

                    //判断是否下载
                    if ("true".equals(down)) {
                        //让浏览器弹出另存为的对话框
                        resp.setContentType("application/octet-stream");
                        //设置对话框的中文名字
                        String fileName = new String(movie.getTitle().getBytes("UTF-8"), "ISO8859-1");
                        resp.addHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");
                        //设置文件的大小，在浏览器上显示进度条
                        resp.setContentLength(new Long(file.length()).intValue());
                    }
                    FileInputStream fileInputStream = new FileInputStream(file);
                    OutputStream outputStream = resp.getOutputStream();//响应输出流

                    IOUtils.copy(fileInputStream,outputStream);

                    outputStream.flush();
                    outputStream.close();
                    fileInputStream.close();

                } else {
                    resp.sendError(404);
                }
            }
        } else {
            resp.sendError(404);
        }
    }

}

