package com.lch.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 境外输入新增
 */
@Data @AllArgsConstructor
public class ImportAddBean {

    private String date;
    private int importedCase;
}
