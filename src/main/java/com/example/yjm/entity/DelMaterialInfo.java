package com.example.yjm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DelMaterialInfo {
    private Integer id;
    private Integer userId;
    private Integer materialId;
    private String name;
    private String detail;
    private String supplier;
    private String specifications;
    private String reason;
}
