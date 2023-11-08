package com.example.yjm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yjm.dao.MaterialsinfoMapper;
import com.example.yjm.dao.OperatelogMapper;
import com.example.yjm.entity.DelMaterialInfo;
import com.example.yjm.entity.Delmaterial;
import com.example.yjm.entity.Materialsinfo;
import com.example.yjm.entity.Operatelog;
import com.example.yjm.service.DelmaterialService;
import com.example.yjm.dao.DelmaterialMapper;
import com.example.yjm.util.CurrentTime;
import com.example.yjm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
* @author DELL
* @description 针对表【delmaterial】的数据库操作Service实现
* @createDate 2023-10-30 10:27:23
*/
@Service
public class DelmaterialServiceImpl extends ServiceImpl<DelmaterialMapper, Delmaterial>
    implements DelmaterialService{

    @Autowired
    private DelmaterialMapper delmaterialMapper;
    @Autowired
    private OperatelogMapper operatelogMapper;
    @Autowired
    private MaterialsinfoMapper materialsinfoMapper;

    @Override
    @Transactional
    public Result saveDelMaterial(Delmaterial delmaterial) {
        LambdaQueryWrapper<Delmaterial> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Delmaterial::getMaterialId,delmaterial.getMaterialId());
        lambdaQueryWrapper.eq(Delmaterial::getIsDel,'0');
        Delmaterial delmaterial1 = delmaterialMapper.selectOne(lambdaQueryWrapper);
        if(!Objects.isNull(delmaterial1)){
            return Result.Fail("已删除，等待管理员审核");
        }
        delmaterial.setDelTime(CurrentTime.getTime());
        boolean save = save(delmaterial);
        if(save){
            Materialsinfo materialsinfo = materialsinfoMapper.selectById(delmaterial.getMaterialId());
            operatelogMapper.insert(new Operatelog(materialsinfo.getUserId(),CurrentTime.getTime(),
                    materialsinfo.getName()+"\t\t物资请求删除待审核"));
            return Result.ok("请求已发送，待审核");
        }
        return Result.err("请求失败");
    }

    @Override
    @Transactional
    public Result delSuccessMaterial(Delmaterial delmaterial) {
        UpdateWrapper<Delmaterial> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("isDel",'1');
        updateWrapper.eq("id",delmaterial.getId());
        boolean update = update(updateWrapper);
        if(update){
            materialsinfoMapper.updateMaterialIsDel(delmaterial.getMaterialId());
            Materialsinfo materialsinfo = materialsinfoMapper.selectById(delmaterial.getMaterialId());
            operatelogMapper.insert(new Operatelog(materialsinfo.getUserId(),CurrentTime.getTime(),
                    materialsinfo.getName()+"\t\t物资审核通过，已删除"));
            return Result.ok("删除成功");
        }
        return Result.err("删除失败");
    }

    @Override
    public Result delFailMaterial(Delmaterial delmaterial) {
        boolean b = removeById(delmaterial.getId());
        if(b){
            Materialsinfo materialsinfo = materialsinfoMapper.selectById(delmaterial.getMaterialId());
            operatelogMapper.insert(new Operatelog(materialsinfo.getUserId(),CurrentTime.getTime(),
                    materialsinfo.getName()+"\t\t物资审不核通过，删除失败"));
            return Result.ok("操作成功");
        }
        return Result.err("操作失败");
    }

    @Override
    public Result delMaterialList() {
        List<DelMaterialInfo> delMaterialInfos = delmaterialMapper.delMaterialList();
        if(!Objects.isNull(delMaterialInfos)){
            return Result.ok(delMaterialInfos);
        }
        return Result.err("数据为空");
    }
}




