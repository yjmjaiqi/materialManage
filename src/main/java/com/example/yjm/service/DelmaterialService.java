package com.example.yjm.service;

import com.example.yjm.entity.Delmaterial;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yjm.util.Result;

/**
* @author DELL
* @description 针对表【delmaterial】的数据库操作Service
* @createDate 2023-10-30 10:27:23
*/
public interface DelmaterialService extends IService<Delmaterial> {
    Result saveDelMaterial(Delmaterial delmaterial);

    Result delSuccessMaterial(Delmaterial delmaterial);

    Result delFailMaterial(Delmaterial delmaterial);

    Result delMaterialList();
}
