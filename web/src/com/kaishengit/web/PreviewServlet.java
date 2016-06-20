package com.kaishengit.web;

import com.kaishengit.entity.Document;
import com.kaishengit.service.DocumentService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/preview")
public class PreviewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String md5 = req.getParameter("file");

        if (StringUtils.isNotEmpty(md5)){
            Document document = new DocumentService().findDocumentByMd5(md5);
            if (document==null){
                resp.sendError(404);
            }else {
                String saveFileName = document.getSavename();
                File file = new File("D:/data",saveFileName);
                if (file.exists()){

                }else {
                    resp.sendError(404);
                }
            }
        }else{
            resp.sendError(404);
        }

    }
}
