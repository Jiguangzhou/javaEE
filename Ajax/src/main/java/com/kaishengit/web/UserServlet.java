package com.kaishengit.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user.xml")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/xml;charset=UTF-8");

        PrintWriter printWriter = resp.getWriter();

        printWriter.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        printWriter.print("<users>");
        printWriter.print("<user id=\"1\"><name>Tom</name><address>USA</address><tel>110</tel></user>");
        printWriter.print("<user id=\"2\"><name>Jack</name><address>Canada</address><tel>119</tel></user>");
        printWriter.print("<user id=\"3\"><name>Lily</name><address>Australia</address><tel>911</tel></user>");
        printWriter.print("<user id=\"4\"><name>小明</name><address>China</address><tel>10086</tel></user>");
        printWriter.print("<user id=\"5\"><name>李华</name><address>China</address><tel>10010</tel></user>");
        printWriter.print("</users>");

        printWriter.flush();
        printWriter.close();
    }
}
