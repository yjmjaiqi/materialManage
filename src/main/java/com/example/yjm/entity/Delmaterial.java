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
 * @TableName delmaterial
 */
@TableName(value ="delmaterial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Delmaterial implements Serializable {
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
    private Integer userId;

    /**
     * 
     */
    private String reason;

    /**
     * 
     */
    private String delTime;

    /**
     * 
     */
    private String isDel;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}