package com.lch.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 每日新增确诊疑似
 */
@Data@AllArgsConstructor
public class AddGraphBean {

    private String date;
    private int confirm;
    private int suspect;
}
