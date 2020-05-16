package com.lch.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 累计确诊、治愈、死亡
 */
@Data@AllArgsConstructor
public class TotalGraphBean {

    private String date;
    private int confirm;
    private int heal;
    private int dead;
}
