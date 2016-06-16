package com.kaishengit.web;


import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/captcha.png")
public class CaptchaServlet extends HttpServlet{
    //产生日志
    private Logger logger = LoggerFactory.getLogger(CaptchaServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConfigurableCaptchaService ccs = new ConfigurableCaptchaService();
        ccs.setColorFactory(new SingleColorFactory(new Color(64, 115,170)));
        ccs.setFilterFactory(new CurvesRippleFilterFactory(ccs.getColorFactory()));

        OutputStream outputStream = resp.getOutputStream();
        String captcha = EncoderHelper.getChallangeAndWriteImage(ccs,"png",outputStream);

        //把产生的验证码放入session里面
        HttpSession session = req.getSession();
        session.setAttribute("captcha",captcha);
        logger.debug("captcha:{}",captcha);

        outputStream.flush();
        outputStream.close();
    }

}
