package com.example.yjm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yjm.controller.SendEmail;
import com.example.yjm.dao.OperatelogMapper;
import com.example.yjm.entity.LoginUser;
import com.example.yjm.entity.Operatelog;
import com.example.yjm.entity.User;
import com.example.yjm.service.UserService;
import com.example.yjm.dao.UserMapper;
import com.example.yjm.util.*;
import com.example.yjm.vo.UserVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
* @author DELL
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-10-16 11:07:46
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private OperatelogMapper operatelogMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 定时任务，每天执行一次
     * 0 表示在每小时的第 0 分钟触发任务。
     * 0/10 表示每 10 分钟触发一次。
     * * 表示每小时都触发。
     * * 表示每天都触发。
     * * 表示每月都触发。
     * ? 表示无需指定具体的月份。
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateUsersDaily() {
        // 在这里编写修改用户信息的逻辑
        // 例如，将所有用户的某个字段设置为新值
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.lt(User::getCreditScore,100);
        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        if(!Objects.isNull(users)){
            for (User user : users) {
                user.setCreditScore(user.getCreditScore() + 1);
                UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("id",user.getId());
                userMapper.update(user,updateWrapper);
            }
        }
        System.out.println("每天执行一次的定时任务：更新用户信息");
    }

    @Override
    @Transactional
    public Result register(User user) {
        String code = SendEmail.verificationCodes.get(user.getEmail());
        if(!code.equals(user.getCode())){
            return Result.codeErr("邮箱验证失败");
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, user.getEmail());
        User user1 = userMapper.selectOne(queryWrapper);
        if(!Objects.isNull(user1)){
            return Result.regFail("邮箱已被注册");
        }
        // user.setPassword(Crypto.encryptToMD5(user.getPassword() + "yjm"));
        user.setPassword(passwordEncoder.encode(user.getPassword() + "yjm"));
        boolean save = save(user);
        if(save){
            return Result.ok("用户注册成功");
        }
        return Result.regFail("注册失败");
    }

    @Override
    // @Transactional
    public Result login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword() + "yjm");
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        User user1 = loginUser.getUser();
        if(!Objects.isNull(loginUser)){
            Integer id = userMapper.roleId(user.getRoleName());
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getEmail, user1.getEmail());
            queryWrapper.eq(User::getRoleId,id);
            User user2 = userMapper.selectOne(queryWrapper);
            if(!Objects.isNull(user2)){
                UserVo copy = BeanUtil.copy(user2, UserVo.class);
                System.out.println(user1);
                user1.setRoleName(user.getRoleName());
                String token = JWTUtil.token(user1.getId().toString());
                System.out.println(JWTUtil.getMsg(token));
                copy.setToken(token);
                // 将用户信息存入redis
                try {
                    redisTemplate.opsForValue().set("loginId:"+user1.getId().toString(),loginUser,1000, TimeUnit.MINUTES);
                    LoginUser loginUser1 = (LoginUser) redisTemplate.opsForValue().get("loginId:" + user1.getId().toString());
                    System.out.println("=========="+loginUser1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return Result.ok(copy);
            }
        }
        return Result.loginFail("登录失败");
    }

    @Override
    public Result findPass(User user) {
        String code = SendEmail.verificationCodes.get(user.getEmail());
        if(!code.equals(user.getCode())){
            return Result.codeErr("邮箱验证失败");
        }
        System.out.println(user);
        user.setPassword(passwordEncoder.encode(user.getPassword() + "yjm"));
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("email",user.getEmail());
        updateWrapper.set("password",user.getPassword());
        boolean update = update(updateWrapper);
        if(update){
            return Result.ok("密码找回成功");
        }
        return Result.err("密码找回失败");
    }

    @Override
    public Result personalInfo(Integer id) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId,id);
        User user = userMapper.selectOne(queryWrapper);
        if(!Objects.isNull(user)){
            return Result.ok(user);
        }
        return Result.err("信息获取失败");
    }

    @Override
    public Result updatePersonalInfo(User user) {
        String code = SendEmail.verificationCodes.get(user.getEmail());
        if(!code.equals(user.getCode())){
            return Result.codeErr("邮箱验证失败");
        }
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",user.getId());
        updateWrapper.set("name", user.getName()).set("gender", user.getGender()).set("birthDate", user.getBirthDate())
                .set("phone", user.getPhone()).set("email", user.getEmail()).set("address", user.getAddress());
        boolean update = update(updateWrapper);
        if(update){
            return Result.ok("修改成功");
        }
        return Result.err("修改错误");
    }

    @Override
    @Transactional
    public Result uploadAvatar(String avatar,Integer id) {
        String avatar1 = userMapper.getAvatar(id);
        if(!Objects.isNull(avatar1)){ // 先从本地删除
            String[] split = avatar1.split("/");
            GetProjectRoot.deleteImg(split[split.length - 1]);
        }
        UpdateWrapper<User> queryWrapper = new UpdateWrapper<>();
        queryWrapper.eq("id",id);
        queryWrapper.set("avatar",avatar);
        boolean update = update(queryWrapper);
        if(update){
            return Result.ok(avatar);
        }
        return Result.err("头像上传失败");
    }

    @Override
    public Result userList(int curPage) {
        if(curPage <= 0){
            curPage = 1;
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getRoleId,2);
        Page<User> page = new Page<>(curPage,Common.SIZE);
        Page<User> page1 = userMapper.selectPage(page, queryWrapper);
        if(page1.getRecords().size() > 0){
            List<User> records = page1.getRecords();
            for(User user : records){
                if(!Objects.isNull(user.getBirthDate())){
                    user.setBirthDate(CurrentTime.format(user.getBirthDate()));
                }
            }
            return Result.ok(page1);
        }
        return Result.err("数据为空");
    }

    @Override
    public Result searchUser(int curPage, String searchContent) {
        if(curPage <= 0){
            curPage = 1;
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getIsDel,0);
        queryWrapper.eq(User::getRoleId,2);
        queryWrapper.like(User::getName,searchContent).or()
                .like(User::getGender,searchContent).or()
                .like(User::getAddress,searchContent);
        Page<User> page = new Page<>(curPage,Common.SIZE);
        Page<User> page1 = userMapper.selectPage(page, queryWrapper);
        if(page1.getRecords().size() > 0){
            System.out.println(Objects.isNull(page1.getRecords()));
            List<User> records = page1.getRecords();
            for(User user : records){
                if(!Objects.isNull(user.getBirthDate())){
                    user.setBirthDate(CurrentTime.format(user.getBirthDate()));
                }
            }
            return Result.ok(page1);
        }
        return Result.err("数据为空");
    }

    @Override
    @Transactional
    public Result reduceUserScore(Integer userid, Integer score) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId,userid);
        User user = userMapper.selectOne(queryWrapper);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",userid);
        updateWrapper.set("creditScore",user.getCreditScore() - score);
        operatelogMapper.insert(new Operatelog(userid,CurrentTime.getTime(), "扣除信誉分:\t\t"+score+"分"));
        boolean update = update(updateWrapper);
        if(update){
            UpdateWrapper<User> updateWrapper1 = new UpdateWrapper<>();
            updateWrapper1.eq("id",userid);
            updateWrapper1.set("violationRecords",user.getViolationRecords() + 1);
            update(updateWrapper1);
            return Result.ok("信誉分扣除完毕");
        }
        return Result.err("操作失败");
    }

    @Override
    @Transactional
    public Result deleteUser(Integer id) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("isDel",1);
        updateWrapper.eq("id",id);
        boolean update = update(updateWrapper);
        operatelogMapper.insert(new Operatelog(id,CurrentTime.getTime(), "账户被冻结"));
        if(update){
            return Result.ok("用户已冻结");
        }
        return Result.err("操作失败");
    }
}




