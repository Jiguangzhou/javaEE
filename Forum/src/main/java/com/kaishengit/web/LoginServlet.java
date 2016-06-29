package com.kaishengit.web;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            // TODO Auto-generated method stub
            response.getWriter().append("Served at: ").append(request.getContextPath());
        }

        /**
         * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
         *      response)
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            String name = request.getParameter("username");
            String pwd = request.getParameter("userpwd");
            String rememberme = request.getParameter("rememberme");
            AdminDAO dao = new AdminDAO();
            if (dao.find(name, pwd)) {
                if("yes".equals(rememberme)){
                    Cookie ck1 = new Cookie("username", name);
                    Cookie ck2 = new Cookie("password", pwd);
                    ck1.setPath("/");
                    ck1.setMaxAge(60 * 60 * 24 * 7);
                    ck1.setHttpOnly(true);
                    ck2.setPath("/");
                    ck2.setMaxAge(60 * 60 * 24 * 7);
                    ck2.setHttpOnly(true);
                    response.addCookie(ck1);
                    response.addCookie(ck2);
                }
                request.getSession().setAttribute("username", name);
                response.sendRedirect("/home");
            } else {
                response.sendRedirect("/index?err=1001");
            }
        }

    }
}
