package com.kaishengit.test;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.io.FileInputStream;

public class CodecTestCase {


    @Test
    public void testMd5(){

        String password = "123123";
        String SALT = "@$!%!^&!^&!^&@&*";
       //password = DigestUtils.md5Hex(password+SALT);
        password = DigestUtils.md5Hex(password);

        System.out.println(password);
    }



    //对比文件生成的md5码
    @Test
    public void testFile() throws Exception {

        FileInputStream inputStream = new FileInputStream("文件路径");
        String md5 = DigestUtils.md5Hex(inputStream);
        System.out.println(md5);

    }
}
