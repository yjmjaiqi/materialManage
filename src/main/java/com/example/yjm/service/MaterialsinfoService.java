package com.example.yjm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yjm.entity.Issuematerial;
import com.example.yjm.entity.Materialsinfo;
import com.example.yjm.util.Result;

/**
* @author DELL
* @description 针对表【materialsinfo】的数据库操作Service
* @createDate 2023-10-17 21:52:31
*/
public interface MaterialsinfoService extends IService<Materialsinfo> {

    // 删除物资
    Result buyMaterial(Materialsinfo materialsinfo);

    // 我的物资
    Result myMaterial(Integer id,int curPage);

    // 我的物资详情
    Result myMaterialDetail(Integer id);

    // 搜索物资
    Result searchMaterial(Integer id,String content,int curPage);

    // 运输中
    Result InTransit(int curPage);

    Result InTransitSearch(String content,int curPage);

    // 已到达
    Result arrived(int curPage);

    Result arrivedSearch(String content,int curPage);

    // 饼图数据
    Result pieChartData();
    //  ------------------------------管理员
    // 审核物资
    Result toExamine(int curPage);

    // 审核通过
    Result distribute(Integer id,String hash,String transactionHash);

    // 搜索待审核物资
    Result searchExamine(Integer id,String content,int curPage);

    // 物资列表
    Result materialList(int curPage);

    Result searchMaterialList(String content,int curPage);

}
