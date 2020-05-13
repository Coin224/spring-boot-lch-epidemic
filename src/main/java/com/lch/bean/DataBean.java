package com.lch.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * domain对象
 * alt+7查看结构体
 */
@Data @AllArgsConstructor
@NoArgsConstructor
@TableName("illness")
public class DataBean implements Serializable {

    private String area;
    private int nowConfirm;
    private int confirm;
    private int heal;//治愈
    private int dead;//死亡
}
