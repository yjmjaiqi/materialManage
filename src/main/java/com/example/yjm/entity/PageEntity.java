package com.example.yjm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageEntity implements Serializable {
    private String token;
    private String searchContent;
    private Integer id;
    private int curPage;
    private int size;
}
