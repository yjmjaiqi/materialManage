package com.example.yjm.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class Test1 {

    @Test
    public void pass() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456yjm");
        System.out.println(encode);
        // boolean matches = bCryptPasswordEncoder.matches("1234567yjm", "$2a$10$MMsV.FCH2rRVWq8M2TF6jODkAd0.e4CUAT/GNAZ2B3rwrS7f2sUZG");
        // System.out.println(matches);
        // Map<String,Integer> map = new HashMap<>();
        // map.put("q",1);
        // map.put("q",2);
        // System.out.println(map.toString());
    }
}
