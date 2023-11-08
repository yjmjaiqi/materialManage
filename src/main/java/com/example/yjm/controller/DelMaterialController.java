package com.example.yjm.controller;

import com.example.yjm.entity.Delmaterial;
import com.example.yjm.service.DelmaterialService;
import com.example.yjm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/material")
public class DelMaterialController {
    @Autowired
    private DelmaterialService delmaterialService;

    @PostMapping("/saveDelMaterial")
    @PreAuthorize("hasAuthority('material')")
    public Result saveDelMaterial(@RequestBody Delmaterial delmaterial){
        return delmaterialService.saveDelMaterial(delmaterial);
    }

    @PostMapping("/delSuccessMaterial")
    @PreAuthorize("hasAuthority('admin')")
    public Result delSuccessMaterial(@RequestBody Delmaterial delmaterial){
        return delmaterialService.delSuccessMaterial(delmaterial);
    }
    @PostMapping("/delFailMaterial")
    @PreAuthorize("hasAuthority('admin')")
    public Result delFailMaterial(@RequestBody Delmaterial delmaterial){
        return delmaterialService.delFailMaterial(delmaterial);
    }

    @GetMapping("/delMaterialList")
    @PreAuthorize("hasAuthority('admin')")
    public Result delMaterialList(){
        return delmaterialService.delMaterialList();
    }
}
