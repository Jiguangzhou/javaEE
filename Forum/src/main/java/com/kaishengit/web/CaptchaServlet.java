package com.kaishengit.web;

import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
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

    private Logger logger = LoggerFactory.getLogger(CaptchaServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConfigurableCaptchaService css = new ConfigurableCaptchaService();
        css.setColorFactory(new SingleColorFactory(new Color(45,32,56)));
        css.setFilterFactory(new CurvesRippleFilterFactory(css.getColorFactory()));

        RandomWordFactory factory = new RandomWordFactory();
        factory.setMinLength(4);
        factory.setMaxLength(4);
        factory.setCharacters("1234567890");
        css.setWordFactory(factory);

        OutputStream outputStream = resp.getOutputStream();
        String captcha = EncoderHelper.getChallangeAndWriteImage(css,"png",outputStream);

        HttpSession session = req.getSession();
        session.setAttribute("captcha",captcha);

        logger.debug("captcha:{}",captcha);

        outputStream.flush();
        outputStream.close();
    }
}
