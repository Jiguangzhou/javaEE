package com.kaishengit.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/pay")
public class PayServlet extends HttpServlet{

    private Logger logger = LoggerFactory.getLogger(PayServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.产生token
        String token = UUID.randomUUID().toString();
        //2.放入session
        HttpSession session = req.getSession();
        session.setAttribute("token",token);
        //3.放入表单
        req.setAttribute("token",token);

        req.getRequestDispatcher("/WEB-INF/views/pay.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.从表单获取token
        String token = req.getParameter("token");
        //2.从session中获取token
        HttpSession session = req.getSession();
        String sessionToken = (String) session.getAttribute("token");
        //3.比较表单中的token是否和session中的token一致
        if(token!=null&&token.equals(sessionToken)){

            //4.删除session中的token
            session.removeAttribute("token");
            String money = req.getParameter("money");

            logger.info("成功扣款{}元",money);

            //请求转发
            req.getRequestDispatcher("/WEB-INF/views/paysuc.jsp").forward(req,resp);
        }else{
            req.getRequestDispatcher("/WEB-INF/views/payerror.jsp").forward(req,resp);
        }


    }
}
