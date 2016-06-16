package com.kaishengit.web;


import com.kaishengit.entity.User;
import com.kaishengit.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

    Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String captcha = req.getParameter("captcha");

        String sessionCaptcha = (String) req.getSession().getAttribute("captcha");

        if(captcha!=null&&captcha.equalsIgnoreCase(sessionCaptcha)){

            String username = req.getParameter("username");
            String password = req.getParameter("password");

            UserService userService = new UserService();
            User user = userService.login(username,password);

            if (user != null){
                logger.debug("显示home页面");
            }else {
                resp.sendRedirect("/login?code=1002");
            }
        }else{
            logger.warn("验证码错误");
            resp.sendRedirect("/login?code=1001");
        }
    }
}
