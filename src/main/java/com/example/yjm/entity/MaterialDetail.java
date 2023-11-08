package com.example.yjm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDetail implements Serializable {
    private User user;
    private Materialsinfo materialsinfo;
    private Issuematerial issuematerial;
    private List<Logmaterial> logmaterials;
}
