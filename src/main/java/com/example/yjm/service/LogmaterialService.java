package com.example.yjm.service;

import com.example.yjm.entity.Logmaterial;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yjm.util.Result;

/**
* @author DELL
* @description 针对表【logmaterial】的数据库操作Service
* @createDate 2023-10-22 14:16:14
*/
public interface LogmaterialService extends IService<Logmaterial> {
    Result recordMaterial(Logmaterial logmaterial);
}
