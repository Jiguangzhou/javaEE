package com.kaishengit.test;

import org.joda.time.DateTime;
import org.junit.Test;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.font.FontFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class MyTestCase {

    @Test
    public void testUIDD(){

        UUID uuid = UUID.randomUUID();
        String key =uuid.toString();

        System.out.print(key);
    }
    @Test
    public void testCaptcha() throws IOException {

        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        cs.setColorFactory(new SingleColorFactory(new Color(25,60,170)));
        cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));

        RandomWordFactory factory = new RandomWordFactory();
        factory.setMinLength(4);
        factory.setMaxLength(6);
        cs.setWordFactory(factory);

        cs.setFontFactory(new FontFactory() {
            @Override
            public Font getFont(int i) {
                return new Font("微软雅黑",Font.ITALIC,24);
            }
        });

        FileOutputStream fos = new FileOutputStream("D:/captcha.png");
        EncoderHelper.getChallangeAndWriteImage(cs,"png",fos);
        fos.flush();
        fos.close();
    }

    @Test
    public void testJodaTime(){
        String now = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
        System.out.println(now);
    }

    @Test
    public void testSubString(){

        String headerValue = "form-data;name=\"doc\";filename=\"images.jpg\"";

        headerValue = headerValue.substring(headerValue.indexOf("filename=\""));
        headerValue = headerValue.substring(headerValue.indexOf("\"")+1,headerValue.length()-1);
        System.out.println(headerValue);

    }
}
