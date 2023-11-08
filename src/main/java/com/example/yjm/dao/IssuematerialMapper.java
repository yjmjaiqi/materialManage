package com.example.yjm.dao;

import com.example.yjm.entity.Issuematerial;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yjm.entity.MaterialDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.parameters.P;

/**
* @author DELL
* @description 针对表【issuematerial】的数据库操作Mapper
* @createDate 2023-10-17 21:52:12
* @Entity com.example.yjm.entity.Issuematerial
*/
public interface IssuematerialMapper extends BaseMapper<Issuematerial> {
}




