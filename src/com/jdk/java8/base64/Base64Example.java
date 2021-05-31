package com.jdk.java8.base64;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Example {

    @Test
    public void test() throws UnsupportedEncodingException {
        //基本
        String s1 = Base64.getEncoder().encodeToString("base64test".getBytes("utf-8"));
        System.out.println(s1);
        System.out.println(new String(Base64.getDecoder().decode(s1), "utf-8"));

        //URL
        String s2 = Base64.getUrlEncoder().encodeToString("base64test".getBytes("utf-8"));
        System.out.println(s2);

        //Mime
        String s3 = Base64.getMimeEncoder().encodeToString("base64test".getBytes("utf-8"));
        System.out.println(s3);

    }

}
