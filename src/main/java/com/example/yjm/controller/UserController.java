package com.example.yjm.controller;

import com.example.yjm.entity.PageEntity;
import com.example.yjm.entity.User;
import com.example.yjm.service.UserService;
import com.example.yjm.util.GetProjectRoot;
import com.example.yjm.util.JWTUtil;
import com.example.yjm.util.LocalIp;
import com.example.yjm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/material")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result login(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public Result logout(@RequestBody User user){
        return userService.login(user);
    }

    @PostMapping("/findPass")
    public Result findPass(@RequestBody User user){
        return userService.findPass(user);
    }

    @GetMapping("/personalInfo")
    @PreAuthorize("hasAuthority('personalInfo')")
    public Result personalInfo(@RequestParam("id") String token){
        String id = JWTUtil.getMsg(token);
        System.out.println(id);
        return userService.personalInfo(Integer.valueOf(id));
    }

    @PutMapping("/updatePersonalInfo")
    // @PreAuthorize("@ex.hasAuthority('updatePersonalInfo')")
    @PreAuthorize("hasAuthority('personalInfo')")
    public Result updatePersonalInfo(@RequestBody User user){
        String id = JWTUtil.getMsg(user.getToken());
        user.setId(Integer.valueOf(id));
        return userService.updatePersonalInfo(user);
    }

    @PostMapping(value = "/uploadAvatar/{userId}")
    @PreAuthorize("hasAuthority('personalInfo')")
    public Result addImg(@RequestPart("file") MultipartFile file, @PathVariable("userId") String token){
        if (file.isEmpty()) {
            return Result.Fail("头像上传失败");
        }
        //可以自己加一点校验 例如上传的是不是图片或者上传的文件是不是png格式等等 这里省略
        //获取原来的文件名和后缀
        String originalFilename = file.getOriginalFilename();
        // 文件后缀
        String ext = "."+ originalFilename.split("\\.")[1];
        //生成一个新的文件名（以防有重复的名字存在导致被覆盖）
        String newName = System.currentTimeMillis() + ext;
        // String pre = "D:\\桌面\\资料\\2023-下\\springcloud\\resource_manage\\synthesize1\\image7001\\src\\main\\resources\\static\\images\\";
        // String path = pre + newName;
        try {
            String ip = LocalIp.ip();
            file.transferTo(new File(GetProjectRoot.getRoot() + newName));
            String imgUrl = "http://"+ip+":8000/images/" + newName;
            System.out.println(token);
            String id = JWTUtil.getMsg(token);
            return userService.uploadAvatar(imgUrl,Integer.valueOf(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.Fail("头像上传失败");
    }

    @GetMapping("/userList")
    @PreAuthorize("hasAuthority('userManage')")
    public Result userList(@RequestParam("curPage") int curPage){
        return userService.userList(curPage);
    }

    @PostMapping("/searchUser")
    @PreAuthorize("hasAuthority('userManage')")
    public Result searchUser(@RequestBody PageEntity pageEntity){
        return userService.searchUser(pageEntity.getCurPage(), pageEntity.getSearchContent());
    }

    @GetMapping("/reduceUserScore")
    @PreAuthorize("hasAuthority('userManage')")
    public Result reduceUserScore(@RequestParam("userId") Integer userId,@RequestParam("score") Integer score){
        return userService.reduceUserScore(userId,score);
    }

    @GetMapping("/deleteUser")
    @PreAuthorize("hasAuthority('userManage')")
    public Result deleteUser(@RequestParam("userId") Integer userId){
        return userService.deleteUser(userId);
    }
}
