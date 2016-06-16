package com.kaishengit.test;

import com.kaishengit.util.EmailUtil;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;

public class MailTestCase {

    @Test
    public void sendTxtMail(){
        SimpleEmail simpleEmail = new SimpleEmail();
        simpleEmail.setAuthentication("jgz1009","mydream1978");
        simpleEmail.setHostName("smtp.126.com");
        simpleEmail.setSmtpPort(25);
        simpleEmail.setCharset("utf-8");

        try {
            simpleEmail.setFrom("jgz1009@126.com");
            simpleEmail.setSubject("今天学习了js加密和邮件发送");
            simpleEmail.setMsg("Hello,JavaMail!");

            simpleEmail.addTo("916780257@qq.com");

            simpleEmail.send();
        } catch (Exception e) {
           throw new RuntimeException();
        }
    }

    @Test
    public void testSendHtmlEmail(){
        EmailUtil.sendHtmlEmail("185869614@qq.com","图片广场","<h3>^_^</h3><img src='http://ww1.sinaimg.cn/mw690/824de770jw1epwcnivby6j20go0p00x4.jpg'/>");
    }

}
