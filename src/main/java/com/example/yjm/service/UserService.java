package com.example.yjm.service;

import com.example.yjm.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yjm.util.Result;

/**
* @author DELL
* @description 针对表【user】的数据库操作Service
* @createDate 2023-10-16 11:07:46
*/
public interface UserService extends IService<User> {
    // 注册
    Result register(User user);
    // 登录
    Result login(User user);
    // 找回密码
    Result findPass(User user);
    // 个人信息展示
    Result personalInfo(Integer id);
    // 修改个人信息
    Result updatePersonalInfo(User user);
    // 上传头像
    Result uploadAvatar(String avatar,Integer id);
    // 用户列表
    Result userList(int curPage);
    // 搜索用户
    Result searchUser(int curPage,String searchContent);
    // 扣除用户信誉分
    Result reduceUserScore(Integer userid,Integer score);
    // 删除用户
    Result deleteUser(Integer id);
}
