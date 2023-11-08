package com.example.yjm.service;

import com.example.yjm.entity.Issuematerial;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yjm.util.Result;

/**
* @author DELL
* @description 针对表【issuematerial】的数据库操作Service
* @createDate 2023-10-22 14:16:04
*/
public interface IssuematerialService extends IService<Issuematerial> {
    // 请求发放
    Result requestDistribute(Issuematerial issuematerial);

    // 审核失败
    Result distributeFail(Integer id);

    // 查询单个
    Result selectMaterialOne(Integer id);
}
