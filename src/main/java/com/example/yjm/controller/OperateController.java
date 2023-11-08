package com.example.yjm.controller;

import com.example.yjm.service.OperatelogService;
import com.example.yjm.util.JWTUtil;
import com.example.yjm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("material")
public class OperateController {
    @Autowired
    private OperatelogService operatelogService;

    @GetMapping("/operateList")
    @PreAuthorize("hasAuthority('material')")
    public Result operateList(@RequestParam("id") String token){
        String id = JWTUtil.getMsg(token);
        return operatelogService.operateList(Integer.valueOf(id));
    }

    @GetMapping("/operateCount")
    @PreAuthorize("hasAuthority('material')")
    public Result operateCount(@RequestParam("id") String token){
        String id = JWTUtil.getMsg(token);
        return operatelogService.operateCount(Integer.valueOf(id));
    }
}
