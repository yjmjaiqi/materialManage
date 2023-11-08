package com.example.yjm.controller;

import com.example.yjm.entity.Materialsinfo;
import com.example.yjm.entity.PageEntity;
import com.example.yjm.service.MaterialsinfoService;
import com.example.yjm.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/material")
public class MaterialsinfoController {
    @Autowired
    private MaterialsinfoService materialsinfoService;

    @PostMapping("/addImg/{id}/{uid}")
    @PreAuthorize("hasAuthority('buyMaterial')")
    public Result addImg(
            @RequestPart("file") MultipartFile file,
            @PathVariable("id") String token,@PathVariable("uid") String uid
    ){
        if (file.isEmpty()) {
            return Result.Fail("图片上传失败");
        }
        System.out.println(uid);
        //可以自己加一点校验 例如上传的是不是图片或者上传的文件是不是png格式等等 这里省略
        //获取原来的文件名和后缀
        String originalFilename = file.getOriginalFilename();
        // 文件后缀
        String ext = "."+ originalFilename.split("\\.")[1];
        //生成一个新的文件名（以防有重复的名字存在导致被覆盖）
        String newName = uid + ext;
        // String pre = "D:\\桌面\\资料\\2023-下\\springcloud\\resource_manage\\synthesize1\\image7001\\src\\main\\resources\\static\\images\\";
        // String path = pre + newName;
        try {
            String ip = LocalIp.ip();
            file.transferTo(new File(GetProjectRoot.getRoot() + newName));
            String imgUrl = "http://"+ip+":8000/images/" + newName + ",";
            return Result.ok(imgUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.err("图片上传失败");
    }

    @PostMapping("/delImg/{id}/{uid}/{name}")
    @PreAuthorize("hasAuthority('buyMaterial')")
    public Result delImg(
            @PathVariable("id") String token,@PathVariable("uid") String uid,
            @PathVariable("name") String name
    ){
        String ext = "."+ name.split("\\.")[1];
        //生成一个新的文件名（以防有重复的名字存在导致被覆盖）
        String newName = uid + ext;
        // String pre = "D:\\桌面\\资料\\2023-下\\springcloud\\resource_manage\\synthesize1\\image7001\\src\\main\\resources\\static\\images\\";
        // String path = pre + newName;
        GetProjectRoot.deleteImg(newName);
        return Result.ok("图片删除成功");
    }

    @PostMapping("/buyMaterial")
    @PreAuthorize("hasAuthority('buyMaterial')")
    public Result buyMaterial(@RequestBody Materialsinfo materialsinfo){
        String id = JWTUtil.getMsg(materialsinfo.getToken());
        materialsinfo.setUserId(Integer.valueOf(id));
        // materialsinfo.setDateOfManufacture(CurrentTime.format(materialsinfo.getDateOfManufacture()));
        return materialsinfoService.buyMaterial(materialsinfo);
    }

    @GetMapping("/myMaterial")
    @PreAuthorize("hasAuthority('myMaterial')")
    public Result myMaterial(@RequestParam("id") String token,@RequestParam("curPage") int curPage){
        String id = JWTUtil.getMsg(token);
        return materialsinfoService.myMaterial(Integer.valueOf(id),curPage);
    }

    @GetMapping("/myMaterialDetail")
    @PreAuthorize("hasAuthority('myMaterial')")
    public Result myMaterialDetail(@RequestParam("id") Integer id){
        return materialsinfoService.myMaterialDetail(id);
    }

    @PostMapping("/searchMaterial")
    @PreAuthorize("hasAuthority('searchMaterial')")
    public Result searchMaterial(@RequestBody PageEntity pageEntity){
        String id = JWTUtil.getMsg(pageEntity.getToken());
        return materialsinfoService.searchMaterial(Integer.valueOf(id),pageEntity.getSearchContent(),pageEntity.getCurPage());
    }

    @GetMapping("/InTransit")
    @PreAuthorize("hasAuthority('material')")
    public Result inTransit(@RequestParam("curPage") int curPage){
        return materialsinfoService.InTransit(curPage);
    }

    @PostMapping("/InTransitSearch")
    @PreAuthorize("hasAuthority('material')")
    public Result InTransitSearch(@RequestBody PageEntity pageEntity){
        return materialsinfoService.InTransitSearch(pageEntity.getSearchContent(),pageEntity.getCurPage());
    }

    @GetMapping("/arrived")
    @PreAuthorize("hasAuthority('material')")
    public Result arrived(@RequestParam("curPage") int curPage){
        return materialsinfoService.arrived(curPage);
    }

    @PostMapping("/arrivedSearch")
    @PreAuthorize("hasAuthority('material')")
    public Result arrivedSearch(@RequestBody PageEntity pageEntity){
        return materialsinfoService.arrivedSearch(pageEntity.getSearchContent(),pageEntity.getCurPage());
    }

    @GetMapping("/pieChartData")
    @PreAuthorize("hasAuthority('material')")
    public Result pieChartData(){
        return materialsinfoService.pieChartData();
    }

    @GetMapping("/toExamine")
    @PreAuthorize("hasAuthority('admin')")
    public Result toExamine(@RequestParam("curPage") int curPage){
        return materialsinfoService.toExamine(curPage);
    }

    @GetMapping("/distribute")
    @PreAuthorize("hasAuthority('admin')")
    public Result distribute(@RequestParam("id") Integer id,
                             @RequestParam("hash") String hash,
                             @RequestParam("transactionHash") String transactionHash){
        return materialsinfoService.distribute(id,hash,transactionHash);
    }

    @PostMapping("/searchExamine")
    @PreAuthorize("hasAuthority('admin')")
    public Result searchExamine(@RequestBody PageEntity pageEntity){
        return materialsinfoService.searchExamine(pageEntity.getId(),pageEntity.getSearchContent(),pageEntity.getCurPage());
    }

    @GetMapping("/materialList")
    @PreAuthorize("hasAuthority('admin')")
    public Result materialList(@RequestParam("curPage") int curPage){
        return materialsinfoService.materialList(curPage);
    }

    @PostMapping("/searchMaterialList")
    @PreAuthorize("hasAuthority('admin')")
    public Result searchMaterialList(@RequestBody PageEntity pageEntity){
        return materialsinfoService.searchMaterialList(pageEntity.getSearchContent(),pageEntity.getCurPage());
    }

}
