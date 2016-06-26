package com.kaishengit.web;


import com.kaishengit.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;


@WebServlet("/servlet/upload")
@MultipartConfig // servlet3.X中用来处理文件提交的注解
public class Servlet3FileUploadServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(Servlet3FileUploadServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/upload3.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        //1.post为上传文件时的表单提交
        String fileDesc = req.getParameter("fileDesc");

        logger.debug("文件的描述为:{}", fileDesc);
        //获取文件的值
        Part part = req.getPart("doc");
        logger.debug("size:{}",part.getSize());
        InputStream input = part.getInputStream();

        MovieService movieService = new MovieService();
        movieService.updateFile(getFileName(part),part.getSize(),input);


//        String cotentType = part.getContentType();
//        logger.debug("文件的ContentType:{}", cotentType);
//        //获取文件大小
//        long size = part.getSize();
//        logger.debug("文件的大小Size:{}", size);
//
//        String fileName = getFileName(part);
//        logger.debug("文件的原始名字:{}", fileName);
//
//        saveFile(part);

    }

//    private void saveFile(Part part) throws IOException {
//
//        File dir = new File("D:/opensource12");
//        if (!dir.exists()) {
//            dir.mkdir();
//        }
//
//        String fileName = getFileName(part);
//        String extName = fileName.substring(fileName.indexOf("."));
//        String uuid = UUID.randomUUID().toString();
//        fileName = uuid + extName;
//
//        InputStream input = part.getInputStream();
//        FileOutputStream output = new FileOutputStream(new File(dir, getFileName(part)));
//
//        IOUtils.copy(input, output);
//       /* BufferedInputStream bufferInput = new BufferedInputStream(input);
//        BufferedOutputStream bufferOutput = new BufferedOutputStream(output);
//
//        byte[] buffer = new byte[1024];
//        int len = -1;
//        while((len = bufferInput.read(buffer))!=-1){
//            bufferOutput.write(buffer,0,len);
//        }**/
//        output.flush();
//        output.close();
//        input.close();
//    }


    private String getFileName(Part part) throws UnsupportedEncodingException{
        String headerValue = part.getHeader("Content-Disposition");
        headerValue = headerValue.substring(headerValue.indexOf("filename=\""));
        headerValue = headerValue.substring(headerValue.indexOf("\"") + 1, headerValue.length() - 1);

        return headerValue;
    }
}
