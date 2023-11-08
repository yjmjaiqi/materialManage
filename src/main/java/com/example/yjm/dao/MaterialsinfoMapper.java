package com.example.yjm.dao;

import com.example.yjm.entity.MaterialDetail;
import com.example.yjm.entity.Materialsinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
* @author DELL
* @description 针对表【materialsinfo】的数据库操作Mapper
* @createDate 2023-10-18 10:49:03
* @Entity com.example.yjm.entity.Materialsinfo
*/
public interface MaterialsinfoMapper extends BaseMapper<Materialsinfo> {

    @Update("update issuematerial set isExamine = #{state},hash = #{hash},transactionHash = #{transactionHash} where materialId = #{id}")
    boolean updateExamine(@Param("id") Integer id,@Param("state") String state,
                          @Param("hash") String hash,@Param("transactionHash") String transactionHash);

    @Update("update materialsinfo set examine = #{state} where id = #{id}")
    boolean updateMaterial(@Param("id") Integer id,@Param("state") String state);

    @Update("update materialsinfo set isDel = '1' where id = #{id}")
    boolean updateMaterialIsDel(@Param("id") Integer id);

    @Select("select count(*) from materialsinfo where isState = '1'")
    Integer inTransitCount();
    @Select("select count(*) from materialsinfo where isState = '2'")
    Integer arrivedCount();
    @Select("select count(*) from materialsinfo where examine = '0'")
    Integer examineCount();
    @Select("select count(*) from materialsinfo where isState = '0'")
    Integer toBeDistributedCount();
}




