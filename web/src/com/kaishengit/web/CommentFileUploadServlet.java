package com.kaishengit.web;

import com.kaishengit.service.DocumentService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class CommentFileUploadServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(CommentFileUploadServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (ServletFileUpload.isMultipartContent(req)) {

            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

            ServletContext servletContext = getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");

            diskFileItemFactory.setRepository(repository);
            diskFileItemFactory.setSizeThreshold(1024);

            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

            try {
                List<FileItem> fileItemList = servletFileUpload.parseRequest(req);
                for (FileItem fileItem : fileItemList) {
                    if (fileItem.isFormField()) {
                        String filedName = fileItem.getFieldName();
                        if ("fileDesc".equals(filedName)) {
                            String value = fileItem.getString("UTF-8");
                            logger.debug("{}->{}", filedName, value);
                        }
                    } else {
                        //代表是上传文件表单元素
                        String fileName = fileItem.getName();
                        long size = fileItem.getSize();
                        String contentType = fileItem.getContentType();
                        logger.debug("fileName:{},Size:{},ContentType:{}", fileName, size, contentType);

                        DocumentService documentService = new DocumentService();
                        documentService.updateFile(fileName, size, fileItem.getInputStream());
                    }
                }
                resp.sendRedirect("/file/list");
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }
    }
}
