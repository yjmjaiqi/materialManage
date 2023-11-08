package com.example.yjm.service;

import com.example.yjm.entity.Operatelog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yjm.util.Result;

/**
* @author DELL
* @description 针对表【operatelog】的数据库操作Service
* @createDate 2023-10-27 15:00:13
*/
public interface OperatelogService extends IService<Operatelog> {
    Result operateList(Integer userId);
    Result operateCount(Integer userId);
}
