package com.example.yjm.dao;

import com.example.yjm.entity.DelMaterialInfo;
import com.example.yjm.entity.Delmaterial;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author DELL
* @description 针对表【delmaterial】的数据库操作Mapper
* @createDate 2023-10-30 10:27:23
* @Entity com.example.yjm.entity.Delmaterial
*/
public interface DelmaterialMapper extends BaseMapper<Delmaterial> {
    @Select("select m.name,m.detail,m.supplier,m.specifications,d.id,d.materialId,d.userId,d.reason from " +
            "delmaterial d inner join materialsinfo m on m.id = d.materialId where d.isDel = '0'")
    List<DelMaterialInfo> delMaterialList();
}




