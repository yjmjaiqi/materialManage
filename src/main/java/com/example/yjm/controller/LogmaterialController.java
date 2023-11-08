package com.example.yjm.controller;

import com.example.yjm.entity.Logmaterial;
import com.example.yjm.service.LogmaterialService;
import com.example.yjm.util.JWTUtil;
import com.example.yjm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/material")
public class LogmaterialController {
    @Autowired
    private LogmaterialService logmaterialService;

    @PostMapping("/recordMaterial")
    @PreAuthorize("hasAuthority('admin')")
    public Result recordMaterial(@RequestBody Logmaterial logmaterial){
        logmaterial.setUserId(JWTUtil.getMsg(logmaterial.getUserId()));
        return logmaterialService.recordMaterial(logmaterial);
        // System.out.println(logmaterial.getReceiveTime());
        // return Result.err("");
    }
}
