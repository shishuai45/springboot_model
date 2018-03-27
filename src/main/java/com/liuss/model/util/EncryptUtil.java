package com.liuss.model.util;

import java.security.MessageDigest;

public class EncryptUtil {
    private final static String ENCRYPT_KEY="f906806fa2cc4011973a144bda24c469";
    public static String Md5(String mes) {
        try {
            mes+=ENCRYPT_KEY;
            MessageDigest md=MessageDigest.getInstance("MD5");
            byte[] bytes=md.digest(mes.getBytes("utf-8"));
            return toHex(bytes);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    private static String toHex(byte[]bytes){
        final char[] HEX_DIGITS="0123456789ABCDEF".toCharArray();
        StringBuilder ret=new StringBuilder(bytes.length*2);
        for (int i=0;i<bytes.length;i++){
            ret.append(HEX_DIGITS[(bytes[i]>>4)&0x0f]);
            ret.append(HEX_DIGITS[bytes[i]&0x0f]);
        }
        return ret.toString();
    }
}
