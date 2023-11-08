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
 * @TableName issuematerial
 */
@TableName(value ="issuematerial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issuematerial implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer materialId;

    /**
     * 
     */
    private String userId;

    /**
     * 始发地
     */
    private String donationSite;

    /**
     * 接收人
     */
    private String receiver;

    /**
     * 接收人手机号
     */
    private String phone;

    /**
     * 目的地
     */
    private String destination;

    /**
     * 发出时间
     */
    private String sendTime;

    /**
     * 
     */
    private String hash;

    /**
     *
     */
    private String transactionHash;

    /**
     * 0审核通过，1未审核
     */
    private String isExamine;

    /**
     * 0删除，1未删除
     */
    private String isDel;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}