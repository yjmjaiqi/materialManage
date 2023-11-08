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
 * @TableName logmaterial
 */
@TableName(value ="logmaterial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Logmaterial implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 记录员
     */
    private String recorder;

    /**
     * 
     */
    private String userId;

    /**
     * 
     */
    private String materialId;

    /**
     * 
     */
    private String phone;

    /**
     * 当前所在地
     */
    private String currentLocation;

    /**
     * 到达时间
     */
    private String receiveTime;

    /**
     * 记录时间
     */
    private String logTime;

    /**
     * 
     */
    private String preHash;

    /**
     * 
     */
    private String hash;

    /**
     *
     */
    private String transactionHash;

    /**
     * 
     */
    private String isDel;

    @TableField(exist = false)
    private String isTrue;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}