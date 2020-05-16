package com.lch.controller;

import com.lch.bean.*;
import com.lch.handler.GraphHandler;
import com.lch.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.tags.Param;

import java.util.*;

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


    @GetMapping("/graphLine")
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
        return "graphline";
    }

    @GetMapping("addgraph")
    public String getAddGraph(Model model) {
        List<String> dateList = new ArrayList<>();
        List<Integer> addConfirmList = new ArrayList<>();
        List<Integer> addSuspectList = new ArrayList<>();
        List<AddGraphBean> list = GraphHandler.getAddGraphBeanList();
        for (int i = 0 ; i < list.size() ; i++){
            AddGraphBean addGraphBean = list.get(i);
            dateList.add(addGraphBean.getDate());
            addConfirmList.add(addGraphBean.getConfirm());
            addSuspectList.add(addGraphBean.getSuspect());
        }
        model.addAttribute("dateList",dateList);
        model.addAttribute("addConfirmList",addConfirmList);
        model.addAttribute("addSuspectList",addSuspectList);
        return "addgraph";
    }

    @GetMapping("totalgraph")
    public String getTotalGraph(Model model) {
        List<String> dateList = new ArrayList<>();
        List<Integer> totalConfirmList = new ArrayList<>();
        List<Integer> totalHealList = new ArrayList<>();
        List<Integer> totalDeadList = new ArrayList<>();
        List<TotalGraphBean> list = GraphHandler.getTotalGraphBeanList();
        for (int i = 0 ; i < list.size() ; i++) {
            TotalGraphBean totalGraphBean = list.get(i);
            dateList.add(totalGraphBean.getDate());
            totalConfirmList.add(totalGraphBean.getConfirm());
            totalHealList.add(totalGraphBean.getHeal());
            totalDeadList.add(totalGraphBean.getDead());
        }
        model.addAttribute("dateList",dateList);
        model.addAttribute("totalConfirmList",totalConfirmList);
        model.addAttribute("totalHealList",totalHealList);
        model.addAttribute("totalDeadList",totalDeadList);
        return "total";
    }
    @GetMapping("healanddeadrate")
    public String getHealAndDeadRate(Model model) {
        List<String> dateList = new ArrayList<>();
        List<String> healRateList = new ArrayList<>();
        List<String> deadRateList = new ArrayList<>();
        List<HealAndDeadRate> list = GraphHandler.getHealAndDeadRateList();
        for (int i = 0 ; i < list.size() ; i++) {
            HealAndDeadRate healAndDeadRate = list.get(i);
            dateList.add(healAndDeadRate.getDate());
            healRateList.add(healAndDeadRate.getHealRate());
            deadRateList.add(healAndDeadRate.getDeadRate());
        }
        model.addAttribute("dateList",dateList);
        model.addAttribute("healRateList",healRateList);
        model.addAttribute("deadRateList",deadRateList);
        return "healanddeadrate";
    }

    @GetMapping("/nowconfirmpie")
    public String getNowConfirmPie(Model model) {
        Map map = GraphHandler.getNowConfirmPie();
        model.addAttribute("gat",map.get("gat"));
        model.addAttribute("import",map.get("import"));
        model.addAttribute("province",map.get("province"));
        return "nowconfirmpie";
    }

    @GetMapping("/importgraph")
    public String getImportGraph(Model model) {
        List<String> areaList = new ArrayList<>();
        List<Integer> comfirmList = new ArrayList<>();
        List<ImportData> list = GraphHandler.getImportList();
        Collections.sort(list);
        for (int i = 0 ; i < 10 ; i ++) {
            ImportData importData = list.get(i);
            areaList.add(importData.getArea());
            comfirmList.add(importData.getImportConfirm());
        }
        model.addAttribute("areaList",areaList);
        model.addAttribute("comfirmList",comfirmList);
        return "importClo";
    }


    @GetMapping("/importaddgraph")
    public String getImportAddGraph(Model model) {
        List<String> dateList = new ArrayList<>();
        List<Integer> importedCaseList = new ArrayList<>();
        List<ImportAddBean> list = GraphHandler.getImportAddList();
        for (int i = 0 ; i < list.size() ; i ++) {
            ImportAddBean importAddBean = list.get(i);
            dateList.add(importAddBean.getDate());
            importedCaseList.add(importAddBean.getImportedCase());
        }
        model.addAttribute("dateList",dateList);
        model.addAttribute("importedCaseList",importedCaseList);
        return "importadd";
    }

    @GetMapping("/totalImportGraph")
    public String getTotalImportGraph(Model model) {
        List<String> dateList = new ArrayList<>();
        List<Integer> importedCaseList = new ArrayList<>();
        List<ImportAddBean> list = GraphHandler.getTotalImportList();
        for (int i = 0 ; i < list.size() ; i ++) {
            ImportAddBean importAddBean = list.get(i);
            dateList.add(importAddBean.getDate());
            importedCaseList.add(importAddBean.getImportedCase());
        }
        model.addAttribute("dateList",dateList);
        model.addAttribute("importedCaseList",importedCaseList);
        return "totalImport";
    }




}
