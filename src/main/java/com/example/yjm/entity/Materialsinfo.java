package com.example.yjm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
 * @TableName materialsinfo
 */
@TableName(value ="materialsinfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Materialsinfo implements Serializable {
    /**
     * 物资ID，自动增长
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 物资名称，不允许为空
     */
    private String name;

    /**
     * 购买者ID
     */
    private Integer userId;

    /**
     * 规格
     */
    private String specifications;

    /**
     * 物资详情
     */
    private String detail;

    /**
     * 生产日期
     */
    private String dateOfManufacture;

    /**
     * 
     */
    private String supplier;

    /**
     * 购买时间
     */
    private String buyTime;

    /**
     * 图片
     */
    private String img;

    /**
     * 0未发放，1运输中，2已到达
     */
    private String isState;

    /**
     * 0未到达资助地，1到达资助地
     */
    private String isOk;

    /**
     * 0未删除，1删除
     */
    private String isDel;

    /**
     * 0待审核，1审核通过，2审核不通过
     */
    private String examine;


    /**
     *
     */
    @TableField(exist = false)
    private String token;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}