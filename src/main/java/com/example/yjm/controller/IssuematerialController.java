package com.example.yjm.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.yjm.entity.Issuematerial;
import com.example.yjm.service.IssuematerialService;
import com.example.yjm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/material")
public class IssuematerialController {
    @Autowired
    private IssuematerialService service;

    @PostMapping("/requestDistribute")
    @PreAuthorize("hasAuthority('distributeMaterial')")
    public Result requestDistribute(@RequestBody Issuematerial issuematerial){
        return service.requestDistribute(issuematerial);
    }

    @GetMapping("/distributeFail")
    @PreAuthorize("hasAuthority('admin')")
    public Result distributeFail(@RequestParam("id") Integer id){
        return service.distributeFail(id);
    }

    @GetMapping("/selectMaterialOne")
    @PreAuthorize("hasAuthority('admin')")
    public Result selectMaterialOne(@RequestParam("id") Integer id){
        return service.selectMaterialOne(id);
    }
}
