package com.lch.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 境外输入确诊病例
 */
@Data @AllArgsConstructor
public class ImportData implements Comparable<ImportData>{
    private String area;
    private int importConfirm;


    @Override
    public int compareTo(ImportData o) {
        return o.importConfirm - this.getImportConfirm();
    }
}
