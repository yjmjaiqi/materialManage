package com.example.yjm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yjm.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
* @author DELL
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-10-16 11:07:46
* @Entity com.example.yjm.entity.User
*/
public interface UserMapper extends BaseMapper<User> {
    @Select("select id from role where roleName = #{roleName}")
    Integer roleId(@Param("roleName") String roleName);

    @Select("select avatar from user where id = #{id}")
    String getAvatar(@Param("id") Integer id);

    // @Select("select id from menu where id < 9")
    // List<Integer> menuIds();
    // @Insert("insert into role_menu (roleId,menuId) values(#{roleId},#{memuId})")
    // boolean insertRoleMenu(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);
}




