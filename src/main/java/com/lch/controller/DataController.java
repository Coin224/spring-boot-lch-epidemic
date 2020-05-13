package com.lch.controller;

import com.lch.bean.DataBean;
import com.lch.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.tags.Param;

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
}
