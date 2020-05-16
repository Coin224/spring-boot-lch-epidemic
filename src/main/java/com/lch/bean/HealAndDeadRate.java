package com.lch.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 治愈率和病死率
 */
@Data @AllArgsConstructor
public class HealAndDeadRate {

    private String date;
    private String healRate;
    private String deadRate;
}
