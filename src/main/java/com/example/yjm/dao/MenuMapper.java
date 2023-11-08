package com.example.yjm.dao;

import com.example.yjm.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author DELL
* @description 针对表【menu】的数据库操作Mapper
* @createDate 2023-10-20 15:05:48
* @Entity com.example.yjm.entity.Menu
*/
public interface MenuMapper extends BaseMapper<Menu> {
    @Select("select distinct m.menuName from user u left join role r on r.id = u.roleId left join role_menu rm " +
            "on rm.roleId = r.id left join menu m on rm.menuId = m.id where m.state = 0 and u.roleId = #{roleId}")
    public List<String> menuList(@Param("roleId") Integer roleId);
}




