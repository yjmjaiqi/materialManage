package com.example.yjm.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @GetMapping("/hello") // 请求方法时拥有对应权限信息
    @PreAuthorize("hasAuthority('personalInfo')")
    public String hello(){
        return "hello";
    }
}
