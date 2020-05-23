package com.learn.provider_service.jpa.common;

import com.thoughtworks.xstream.core.util.Base64Encoder;

import java.security.MessageDigest;

public class MD5Utils {
    //MD5加密
    public static String EncoderByMD5(String string) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        Base64Encoder base64en=new Base64Encoder();
        String newStr = base64en.encode(messageDigest.digest(string.getBytes("utf-8")));
        return newStr;
    }
    //判断用户名密码是否正确
    public static boolean checkPassword(String newPassword,String oldpassword) throws Exception {
        if (EncoderByMD5(newPassword).equals(oldpassword)) {
            return true;
        }

        return false;
    }
    public static void main(String[] args) throws Exception {
        String md5 = MD5Utils.EncoderByMD5("1234");
        System.out.println(md5);
        boolean b = MD5Utils.checkPassword("1234", "gdyb21LQTcIANtvYMT7QVQ==");
        System.out.println(b);

    }
}
