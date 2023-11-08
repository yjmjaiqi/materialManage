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
 * @TableName user
 */
@TableName(value ="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String gender;

    /**
     * 
     */
    private String birthDate;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String phone;

    /**
     * 
     */
    private String avatar;

    /**
     * 
     */
    private String address;

    /**
     * 
     */
    private Integer creditScore;

    /**
     * 
     */
    private Integer roleId;

    /**
     *
     */
    private String isDel;


    /**
     *
     */
    @TableField(exist = false)
    private String code;

    /**
     *
     */
    @TableField(exist = false)
    private String token;

    /**
     * 违规次数
     */
    private int violationRecords;
    /**
     *
     */
    @TableField(exist = false)
    private String roleName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}