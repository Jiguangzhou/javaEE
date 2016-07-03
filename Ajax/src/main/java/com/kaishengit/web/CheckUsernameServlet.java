package com.kaishengit.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/checkusername")
public class CheckUsernameServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(CheckUsernameServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理服务器缓存问题
        resp.setContentType("text/html;UTF-8");
        resp.addHeader("pragma","no-cache");
        resp.addHeader("cache-control","no-cache");
        resp.addHeader("expires","0");

        String username  = req.getParameter("username");
        username = new String(username.getBytes("ISO8859-1"),"UTF-8");
        logger.debug("username:{}",username);

        PrintWriter out = resp.getWriter();
        if ("Tom".equals(username)){
            out.print("false");
        }else{
            out.print("true");
        }
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        logger.debug("doPost request...{}-{}",name,address);
    }
}
