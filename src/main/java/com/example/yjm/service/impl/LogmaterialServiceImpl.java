package com.example.yjm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yjm.dao.LogmaterialMapper;
import com.example.yjm.dao.MaterialsinfoMapper;
import com.example.yjm.dao.OperatelogMapper;
import com.example.yjm.dao.UserMapper;
import com.example.yjm.entity.Logmaterial;
import com.example.yjm.entity.Materialsinfo;
import com.example.yjm.entity.Operatelog;
import com.example.yjm.entity.User;
import com.example.yjm.service.LogmaterialService;
import com.example.yjm.util.CurrentTime;
import com.example.yjm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author DELL
* @description 针对表【logmaterial】的数据库操作Service实现
* @createDate 2023-10-22 14:16:14
*/
@Service
public class LogmaterialServiceImpl extends ServiceImpl<LogmaterialMapper, Logmaterial>
    implements LogmaterialService{

    @Autowired
    private MaterialsinfoMapper materialsinfoMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OperatelogMapper operatelogMapper;

    @Override
    @Transactional
    public Result recordMaterial(Logmaterial logmaterial) {
        if(logmaterial.getIsTrue().equals("是")){
            Materialsinfo materialsinfo = materialsinfoMapper.selectById(logmaterial.getMaterialId());
            UpdateWrapper<Materialsinfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id",logmaterial.getMaterialId());
            updateWrapper.set("isState",'2');
            materialsinfoMapper.update(materialsinfo,updateWrapper);
        }
        User user = userMapper.selectById(logmaterial.getUserId());
        logmaterial.setRecorder(user.getName());
        logmaterial.setPhone(user.getPhone());
        logmaterial.setLogTime(CurrentTime.getTime());
        Materialsinfo materialsinfo = materialsinfoMapper.selectById(logmaterial.getMaterialId());
        operatelogMapper.insert(new Operatelog(materialsinfo.getUserId(),CurrentTime.getTime(),
                materialsinfo.getName()+"\t\t物资状态已更新,"+"当前所在地:\t\t"+logmaterial.getCurrentLocation()));
        boolean save = save(logmaterial);
        if(save){
            return Result.ok("记录成功");
        }
        return Result.err("记录失败");
    }
}




