package com.example.yjm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yjm.dao.IssuematerialMapper;
import com.example.yjm.dao.MaterialsinfoMapper;
import com.example.yjm.dao.OperatelogMapper;
import com.example.yjm.entity.Issuematerial;
import com.example.yjm.entity.Materialsinfo;
import com.example.yjm.entity.Operatelog;
import com.example.yjm.service.IssuematerialService;
import com.example.yjm.util.Common;
import com.example.yjm.util.CurrentTime;
import com.example.yjm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
* @author DELL
* @description 针对表【issuematerial】的数据库操作Service实现
* @createDate 2023-10-22 14:16:04
*/
@Service
public class IssuematerialServiceImpl extends ServiceImpl<IssuematerialMapper, Issuematerial>
    implements IssuematerialService{

    @Autowired
    private IssuematerialMapper issuematerialMapper;
    @Autowired
    private MaterialsinfoMapper materialsinfoMapper;
    @Autowired
    private OperatelogMapper operatelogMapper;

    @Override
    @Transactional
    public Result requestDistribute(Issuematerial issuematerial) {
        issuematerial.setSendTime(CurrentTime.getTime());
        boolean save = save(issuematerial);
        LambdaQueryWrapper<Materialsinfo> queryWrapper = new LambdaQueryWrapper<>();
        materialsinfoMapper.updateMaterial(issuematerial.getMaterialId(),"0");
        if(save){
            return Result.ok("请求已发送，待审核");
        }
        return Result.err("请求失败");
    }

    @Override
    @Transactional
    public Result distributeFail(Integer id) {
        LambdaQueryWrapper<Issuematerial> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Issuematerial::getMaterialId,id);
        int i = issuematerialMapper.delete(lambdaQueryWrapper);
        Materialsinfo materialsinfo = materialsinfoMapper.selectById(id);
        operatelogMapper.insert(new Operatelog(materialsinfo.getUserId(),CurrentTime.getTime(),
                materialsinfo.getName()+"\t\t物资审核不通过"));
        if(i > 0){
            materialsinfoMapper.updateMaterial(id,"");
            return Result.ok("操作成功");
        }
        return Result.err("操作失败");
    }

    @Override
    public Result selectMaterialOne(Integer id) {
        LambdaQueryWrapper<Issuematerial> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Issuematerial::getMaterialId,id);
        lambdaQueryWrapper.eq(Issuematerial::getIsDel,'0');
        Issuematerial issuematerial = issuematerialMapper.selectOne(lambdaQueryWrapper);
        if(!Objects.isNull(issuematerial)){
            return Result.ok(issuematerial);
        }
        return Result.err("数据为空");
    }
}




