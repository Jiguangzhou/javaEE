package com.kaishengit.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailUtil {


    private static Logger logger = LoggerFactory.getLogger(EmailUtil.class);
    /**
     *
     * @param toAddress 收件人地址
     * @param subject 邮件主题
     * @param html 邮件格式
     */
    public static void sendHtmlEmail(String toAddress,String subject,String html){

        HtmlEmail htmlEmail = new HtmlEmail();
        htmlEmail.setHostName(Config.get("simpleEmail.hostname"));
        htmlEmail.setSmtpPort(Integer.parseInt(Config.get("simpleEmail.port","25")));
        htmlEmail.setAuthentication(Config.get("simpleEmail.username"),Config.get("simpleEmail.password"));
        htmlEmail.setCharset(Config.get("simpleEmail.charset"));

        try {
            htmlEmail.setFrom(Config.get("simpleEmail.fromEmail"));
            htmlEmail.setSubject(subject);
            htmlEmail.setHtmlMsg(html);
            htmlEmail.addTo(toAddress);
            htmlEmail.send();

            logger.debug("给{}发送了一封邮件",toAddress);
        } catch (EmailException e) {
            logger.debug("给{}发送邮件失败",toAddress);
           throw new RuntimeException("发送邮件异常",e);
        }
    }
}
