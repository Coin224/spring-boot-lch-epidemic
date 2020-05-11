package com.lch.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * domain对象
 */
@Data @AllArgsConstructor
public class DataBean {
    private String area;
    private int nowConfirm;
    private int confirm;
    private int heal;//治愈
    private int dead;//死亡
}
