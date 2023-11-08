package com.example.yjm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yjm.dao.*;
import com.example.yjm.entity.*;
import com.example.yjm.service.MaterialsinfoService;
import com.example.yjm.util.Common;
import com.example.yjm.util.CurrentTime;
import com.example.yjm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
* @author DELL
* @description 针对表【materialsinfo】的数据库操作Service实现
* @createDate 2023-10-17 21:52:31
*/
@Service
public class MaterialsinfoServiceImpl extends ServiceImpl<MaterialsinfoMapper, Materialsinfo>
    implements MaterialsinfoService{


    @Autowired
    private MaterialsinfoMapper materialsinfoMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LogmaterialMapper logmaterialMapper;
    @Autowired
    private IssuematerialMapper issuematerialMapper;
    @Autowired
    private OperatelogMapper operatelogMapper;

    @Override
    @Transactional
    public Result buyMaterial(Materialsinfo materialsinfo) {
        User user = userMapper.selectById(materialsinfo.getUserId());
        if(!Objects.isNull(user) && user.getCreditScore() < 90){
            return Result.err("信誉分太低");
        }
        materialsinfo.setBuyTime(CurrentTime.getTime());
        boolean save = save(materialsinfo);
        if(save){
            return Result.ok("购买成功");
        }
        return Result.err("购买失败");
    }

    @Override
    public Result myMaterial(Integer id,int curPage) {
        if(curPage <= 0){
            curPage = 1;
        }
        LambdaQueryWrapper<Materialsinfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Materialsinfo::getUserId,id);
        queryWrapper.eq(Materialsinfo::getIsDel,'0');
        queryWrapper.orderByAsc(Materialsinfo::getIsState);
        Page<Materialsinfo> page = new Page<>(curPage, Common.SIZE);
        Page<Materialsinfo> page1 = materialsinfoMapper.selectPage(page, queryWrapper);
        if(page1.getRecords().size() > 0){
            return Result.ok(page1);
        }
        return Result.err("数据为空");
    }

    @Override
    @Transactional
    public Result myMaterialDetail(Integer id) {
        Materialsinfo materialsinfo = materialsinfoMapper.selectById(id);
        if(!Objects.isNull(materialsinfo)){
            Integer userId = materialsinfo.getUserId();
            User user = userMapper.selectById(userId);
            LambdaQueryWrapper<Issuematerial> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Issuematerial::getIsDel,'0');
            lambdaQueryWrapper.eq(Issuematerial::getMaterialId,id);
            Issuematerial issuematerial = issuematerialMapper.selectOne(lambdaQueryWrapper);
            LambdaQueryWrapper<Logmaterial> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
            lambdaQueryWrapper1.eq(Logmaterial::getIsDel,'0');
            lambdaQueryWrapper1.eq(Logmaterial::getMaterialId,id);
            lambdaQueryWrapper1.orderByDesc(Logmaterial::getLogTime).orderByDesc(Logmaterial::getReceiveTime);
            List<Logmaterial> logmaterials = logmaterialMapper.selectList(lambdaQueryWrapper1);
            MaterialDetail materialDetail = new MaterialDetail();
            materialDetail.setMaterialsinfo(materialsinfo);
            materialDetail.setUser(user);
            materialDetail.setIssuematerial(issuematerial);
            materialDetail.setLogmaterials(logmaterials);
            return Result.ok(materialDetail);
        }
        return Result.err("数据为空");
    }

    @Override
    public Result searchMaterial(Integer id,String content,int curPage) {
        if(curPage <= 0){
            curPage = 1;
        }
        LambdaQueryWrapper<Materialsinfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Materialsinfo::getName,content)
                .or().like(Materialsinfo::getDetail,content)
                .or().like(Materialsinfo::getSupplier,content)
                .eq(Materialsinfo::getIsDel,'0')
                .eq(Materialsinfo::getUserId,id);
        Page<Materialsinfo> page = new Page<>(curPage,Common.SIZE);
        Page<Materialsinfo> page1 = materialsinfoMapper.selectPage(page, queryWrapper);
        if(page1.getRecords().size() > 0){
            return Result.ok(page1);
        }
        return Result.err("数据为空");
    }

    @Override
    public Result InTransit(int curPage) {
        if(curPage <= 0){
            curPage = 1;
        }
        LambdaQueryWrapper<Materialsinfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Materialsinfo::getIsDel,'0');
        queryWrapper.eq(Materialsinfo::getIsState,'1');
        Page<Materialsinfo> page = new Page<>(curPage,Common.SIZE);
        Page<Materialsinfo> page1 = materialsinfoMapper.selectPage(page, queryWrapper);
        if(page1.getRecords().size() > 0){
            return Result.ok(page1);
        }
        return Result.err("数据为空");
    }

    @Override
    public Result InTransitSearch(String content, int curPage) {
        if(curPage <= 0){
            curPage = 1;
        }
        LambdaQueryWrapper<Materialsinfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Materialsinfo::getIsDel,'0');
        queryWrapper.eq(Materialsinfo::getIsState,'1');
        queryWrapper.like(Materialsinfo::getName,content)
                .or().like(Materialsinfo::getDetail,content)
                .or().like(Materialsinfo::getSupplier,content);
        Page<Materialsinfo> page = new Page<>(curPage,Common.SIZE);
        Page<Materialsinfo> page1 = materialsinfoMapper.selectPage(page, queryWrapper);
        if(page1.getRecords().size() > 0){
            return Result.ok(page1);
        }
        return Result.err("数据为空");
    }

    @Override
    public Result arrived(int curPage) {
        if(curPage <= 0){
            curPage = 1;
        }
        LambdaQueryWrapper<Materialsinfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Materialsinfo::getIsDel,'0');
        queryWrapper.eq(Materialsinfo::getIsState,'2');
        Page<Materialsinfo> page = new Page<>(curPage,Common.SIZE);
        Page<Materialsinfo> page1 = materialsinfoMapper.selectPage(page, queryWrapper);
        if(page1.getRecords().size() > 0){
            return Result.ok(page1);
        }
        return Result.err("数据为空");
    }

    @Override
    public Result arrivedSearch(String content, int curPage) {
        if(curPage <= 0){
            curPage = 1;
        }
        LambdaQueryWrapper<Materialsinfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Materialsinfo::getIsDel,'0');
        queryWrapper.eq(Materialsinfo::getIsState,'2');
        queryWrapper.like(Materialsinfo::getName,content)
                .or().like(Materialsinfo::getDetail,content)
                .or().like(Materialsinfo::getSupplier,content);
        Page<Materialsinfo> page = new Page<>(curPage,Common.SIZE);
        Page<Materialsinfo> page1 = materialsinfoMapper.selectPage(page, queryWrapper);
        if(page1.getRecords().size() > 0){
            return Result.ok(page1);
        }
        return Result.err("数据为空");
    }

    @Override
    @Transactional
    public Result pieChartData() {
        Map<String,Integer> map = new HashMap<>();
        Integer inTransitCount = materialsinfoMapper.inTransitCount();
        Integer arrivedCount = materialsinfoMapper.arrivedCount();
        Integer examineCount = materialsinfoMapper.examineCount();
        Integer toBeDistributedCount = materialsinfoMapper.toBeDistributedCount();
        map.put("inTransitCount",inTransitCount);
        map.put("arrivedCount",arrivedCount);
        map.put("examineCount",examineCount);
        map.put("toBeDistributedCount",toBeDistributedCount);
        return Result.ok(map);
    }


    @Override
    public Result toExamine(int curPage) {
        if(curPage <= 0){
            curPage = 1;
        }
        LambdaQueryWrapper<Materialsinfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Materialsinfo::getIsDel,'0');
        queryWrapper.eq(Materialsinfo::getIsState,'0');
        queryWrapper.eq(Materialsinfo::getExamine,'0');
        Page<Materialsinfo> page = new Page<>(curPage, Common.SIZE);
        Page<Materialsinfo> page1 = materialsinfoMapper.selectPage(page, queryWrapper);
        if(page1.getRecords().size() > 0){
            return Result.ok(page1);
        }
        return Result.err("数据为空");
    }


    @Override
    @Transactional
    public Result distribute(Integer id,String hash,String transactionHash) {
        UpdateWrapper<Materialsinfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        updateWrapper.set("isState",'1');
        updateWrapper.set("examine",'1');
        Materialsinfo materialsinfo = materialsinfoMapper.selectById(id);
        boolean update = update(updateWrapper);
        operatelogMapper.insert(new Operatelog(materialsinfo.getUserId(),CurrentTime.getTime(),
                materialsinfo.getName()+"\t\t物资审核通过,已上链"));
        if(update){
            materialsinfoMapper.updateExamine(id,"1",hash,transactionHash);
            return Result.ok("已发放");
        }
        return Result.err("失败");
    }

    @Override
    public Result searchExamine(Integer id, String content, int curPage) {
        if(curPage <= 0){
            curPage = 1;
        }
        LambdaQueryWrapper<Materialsinfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Materialsinfo::getName,content)
                .or().like(Materialsinfo::getDetail,content)
                .or().like(Materialsinfo::getSupplier,content)
                .eq(Materialsinfo::getIsDel,'0')
                .eq(Materialsinfo::getUserId,id)
                .eq(Materialsinfo::getIsState,'0');
        Page<Materialsinfo> page = new Page<>(curPage,Common.SIZE);
        Page<Materialsinfo> page1 = materialsinfoMapper.selectPage(page, queryWrapper);
        if(page1.getRecords().size() > 0){
            return Result.ok(page1);
        }
        return Result.err("数据为空");
    }

    @Override
    public Result materialList(int curPage) {
        if(curPage <= 0){
            curPage = 1;
        }
        Page<Materialsinfo> page = new Page<>(curPage,Common.SIZE);
        LambdaQueryWrapper<Materialsinfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Materialsinfo::getIsDel,'0');
        Page<Materialsinfo> page1 = materialsinfoMapper.selectPage(page, lambdaQueryWrapper);
        if(page1.getRecords().size() >0){
            return Result.ok(page1);
        }
        return Result.err("数据为空");
    }

    @Override
    public Result searchMaterialList(String content, int curPage) {
        if(curPage <= 0){
            curPage = 1;
        }
        LambdaQueryWrapper<Materialsinfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Materialsinfo::getName,content)
                .or().like(Materialsinfo::getDetail,content)
                .or().like(Materialsinfo::getSupplier,content)
                .eq(Materialsinfo::getIsDel,'0');
        Page<Materialsinfo> page = new Page<>(curPage,Common.SIZE);
        Page<Materialsinfo> page1 = materialsinfoMapper.selectPage(page, queryWrapper);
        if(page1.getRecords().size() > 0){
            return Result.ok(page1);
        }
        return Result.err("数据为空");
    }

}




