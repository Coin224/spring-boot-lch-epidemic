package com.lch.controller;

import com.lch.bean.DataBean;
import com.lch.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class DataController {

    //自动注入值
    @Autowired
    private DataService dataService;

    @GetMapping("/")
    public String getList(Model model) {
        Map<String, Object> resultMap = dataService.getData();
        List<DataBean> resultList = (List<DataBean>) resultMap.get("resultList");
        String lastUpdateTime = (String) resultMap.get("lastUpdateTime");
        model.addAttribute("dataList",resultList);
        model.addAttribute("lastUpdateTime",lastUpdateTime);
        return "list";
    }
}
