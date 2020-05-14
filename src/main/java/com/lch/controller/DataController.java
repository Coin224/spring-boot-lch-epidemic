package com.lch.controller;

import com.lch.bean.DataBean;
import com.lch.bean.GraphBean;
import com.lch.handler.GraphHandler;
import com.lch.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.tags.Param;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DataController {

    //自动注入值
    @Autowired
    private DataService dataService;

    @GetMapping("/")
    public String getList(Model model) {
        List<DataBean> dataBeanList= dataService.list();
        model.addAttribute("dataList",dataBeanList);
        return "list";
    }

//    @GetMapping("/{id}")
//    public String listById(Model model, @PathVariable String id) {//@PathVariable 前端传来的数据接收
//        List<DataBean> dataBeanList= dataService.listById(Integer.parseInt(id));
//        model.addAttribute("dataList",dataBeanList);
//        return "list";
//    }


    @GetMapping("/graph")
    public String getGraph (Model model) {
        List<GraphBean> list = GraphHandler.getGraphData();
        //进一步改造数据格式
        //因为前段需要的数据是 x轴所有数据的数组和y轴所有数据的数组
        List<String> dateList = new ArrayList<>();
        List<Integer> nowConfirmList = new ArrayList<>();
        for (int i = 0 ; i < list.size() ; i++) {
            GraphBean graphBean = list.get(i);
            dateList.add(graphBean.getDate());
            nowConfirmList.add(graphBean.getNowConfirm());
        }
        model.addAttribute("dateList",dateList);
        model.addAttribute("nowConfirmList",nowConfirmList);
        return "graph";
    }
}
