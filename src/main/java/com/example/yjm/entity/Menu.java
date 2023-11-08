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
 * @TableName menu
 */
@TableName(value ="menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String menuName;

    /**
     * 0未停用，1停用
     */
    private String state;

    /**
     * 0未未删除，1删除
     */
    private String isDel;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}