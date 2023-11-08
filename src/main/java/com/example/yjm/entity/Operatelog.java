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
 * @TableName operatelog
 */
@TableName(value ="operatelog")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operatelog implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer userid;

    /**
     * 
     */
    private String time;

    /**
     * 
     */
    private String message;

    /**
     *
     */
    private String isDel;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Operatelog(Integer userid, String time, String message) {
        this.userid = userid;
        this.time = time;
        this.message = message;
    }
}