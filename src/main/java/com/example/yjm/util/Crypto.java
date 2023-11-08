package com.example.yjm.util;

import org.apache.commons.codec.digest.DigestUtils;


// 密码加密
public class Crypto {
    public static String encryptToMD5(String str) {

        return DigestUtils.md5Hex(str);
    }
}
