package com.lch.handler;

import com.google.gson.Gson;
import com.lch.bean.DataBean;
import com.lch.service.DataService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 使用Jsoup工具解析Html数据
 */
@Component //把这个类变成一个组件
public class JsoupHandler {

    @Autowired
    private DataService dataService;

    @PostConstruct//在服务器加载servlet时运行，而且只执行一次
    public void saveData() {
        List<DataBean> dataBeans = getData();
        //先将表中的数据删除，然后存储数据
        dataService.remove(null);//删除表中数据
        dataService.saveBatch(dataBeans);
    }

    @Scheduled(cron = "0/1 * * * * ? ")
    public void updateData() {
        saveData();
    }





    private static String htmlStr = "https://ncov.dxy.cn/ncovh5/view/pneumonia?from=timeline&isappinstalled=0";//丁香园数据网址
    public static List<DataBean> getData() {

        // 此集合用来存放返回的数据
        List<DataBean> dataBeanList = new ArrayList<>();
        try {
            // 1. 获取到document对象
            Document document = Jsoup.connect(htmlStr).get();
            // 2. 使用document对象获取数据所在的标签 及数据
            Element element = document.getElementById("getAreaStat");
            String data = element.data();
            String subData = data.substring(data.indexOf("["),data.lastIndexOf("]")+1);
            // 3.使用Gson来解析这个Html数据
            Gson gson = new Gson();
            ArrayList arrayList = gson.fromJson(subData, ArrayList.class);
            //遍历这个集合 并且把集合中得到的数据放到

            for (int i = 0 ; i < arrayList.size() ; i ++) {
                Map dataMap = (Map) arrayList.get(i);
                String area = (String) dataMap.get("provinceName");
                double nowConfirm = (double) dataMap.get("currentConfirmedCount");
                double confirm = (double) dataMap.get("confirmedCount");
                double heal = (double) dataMap.get("curedCount");
                double dead = (double) dataMap.get("deadCount");
                DataBean dataBean = new DataBean(area,(int)nowConfirm,(int)confirm,(int)heal,(int)dead);
                dataBeanList.add(dataBean);
            }
            System.out.println(dataBeanList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataBeanList;
    }



    public static void main(String[] args) {
        getData();
    }
}
