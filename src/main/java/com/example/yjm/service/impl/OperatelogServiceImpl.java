package com.example.yjm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yjm.entity.Operatelog;
import com.example.yjm.service.OperatelogService;
import com.example.yjm.dao.OperatelogMapper;
import com.example.yjm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
* @author DELL
* @description 针对表【operatelog】的数据库操作Service实现
* @createDate 2023-10-27 15:00:13
*/
@Service
public class OperatelogServiceImpl extends ServiceImpl<OperatelogMapper, Operatelog>
    implements OperatelogService{

    @Autowired
    private OperatelogMapper operatelogMapper;

    @Override
    public Result operateList(Integer userId) {
        LambdaQueryWrapper<Operatelog> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Operatelog::getUserid,userId);
        lambdaQueryWrapper.eq(Operatelog::getIsDel,'0');
        lambdaQueryWrapper.orderByDesc(Operatelog::getTime);
        List<Operatelog> operatelogs = operatelogMapper.selectList(lambdaQueryWrapper);
        if(!Objects.isNull(operatelogs)){
            return Result.ok(operatelogs);
        }
        return Result.err("数据为空");
    }

    @Override
    public Result operateCount(Integer userId) {
        LambdaQueryWrapper<Operatelog> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Operatelog::getUserid,userId);
        lambdaQueryWrapper.eq(Operatelog::getIsDel,'0');
        Integer operateCount = operatelogMapper.selectCount(lambdaQueryWrapper);
        if(operateCount > 0){
            return Result.ok(operateCount);
        }
        return Result.err("数据为空");
    }
}




