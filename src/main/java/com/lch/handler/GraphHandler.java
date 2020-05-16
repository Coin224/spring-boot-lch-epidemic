package com.lch.handler;

import com.google.gson.Gson;
import com.lch.bean.*;
import com.lch.util.HttpClientUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 这个类是处理图形数据的类
 */
public class GraphHandler {

    //从这个网址拿到数据
    private static String url = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_other";
    private static String h5url = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";


    private static Map getDataMap () {
        //用Util中的工具来获得数据
        String str = HttpClientUtil.doGet(url);

        // 通过Gson来解析数据
        Gson gson = new Gson();
        Map map = gson.fromJson(str, Map.class);

        //获取map中的data
        String data = (String) map.get("data");
        return gson.fromJson(data, Map.class);
    }
    private static Map getH5DataMap () {
        //用Util中的工具来获得数据
        String str = HttpClientUtil.doGet(h5url);

        // 通过Gson来解析数据
        Gson gson = new Gson();
        Map map = gson.fromJson(str, Map.class);

        //获取map中的data
        String data = (String) map.get("data");
        return gson.fromJson(data, Map.class);
    }



    public static List<GraphBean> getGraphData () {
        List<GraphBean> result = new ArrayList<>();
        Map map = getDataMap();

        ArrayList list = (ArrayList) map.get("chinaDayList");
        //遍历list
        for (int i = 0; i < list.size() ; i++) {
            Map graphMap = (Map) list.get(i);
            String date = (String) graphMap.get("date");
            double nowConfirm = (double) graphMap.get("nowConfirm");
            GraphBean graphBean = new GraphBean(date,(int)nowConfirm);
            result.add(graphBean);
        }
        return result;

    }


    public static List<AddGraphBean> getAddGraphBeanList() {
        List<AddGraphBean> result = new ArrayList<>();
        Map map = getDataMap();
        ArrayList chinaDayAddList = (ArrayList) map.get("chinaDayAddList");
        //遍历 list 获取每个子元素中的有用元素
        for (int i = 0 ;i <chinaDayAddList.size() ; i++) {
            Map addMap = (Map) chinaDayAddList.get(i);
            String date = (String) addMap.get("date");
            double addConfirm = (double) addMap.get("confirm");
            double addSuspect = (double) addMap.get("suspect");
            AddGraphBean addGraphBean = new AddGraphBean(date,(int)addConfirm,(int)addSuspect);
            result.add(addGraphBean);
        }
        return result;
    }


    public static List<TotalGraphBean> getTotalGraphBeanList() {
        List<TotalGraphBean> result = new ArrayList<>();
        Map map = getDataMap();
        ArrayList chinaDayList = (ArrayList) map.get("chinaDayList");
        for (int i = 0 ; i < chinaDayList.size() ; i++) {
            Map totalMap = (Map) chinaDayList.get(i);
            String date = (String) totalMap.get("date");
            double confirm = (double) totalMap.get("confirm");
            double heal = (double) totalMap.get("heal");
            double dead = (double) totalMap.get("dead");
            TotalGraphBean totalGraphBean = new TotalGraphBean(date,(int)confirm,(int)heal,(int)dead);
            result.add(totalGraphBean);
        }
        return result;
    }
    public static List<HealAndDeadRate> getHealAndDeadRateList() {
        List<HealAndDeadRate> result = new ArrayList<>();
        Map map = getDataMap();
    ArrayList chinaDayList = (ArrayList) map.get("chinaDayList");
        for (int i = 0 ; i < chinaDayList.size() ; i++) {
        Map rateMap = (Map) chinaDayList.get(i);
        String date = (String) rateMap.get("date");
        String healRate = (String) rateMap.get("healRate");
        String deadRate = (String) rateMap.get("deadRate");
        HealAndDeadRate healAndDeadRate = new HealAndDeadRate(date,healRate,deadRate);
        result.add(healAndDeadRate);
        }
        return result;
    }

    public static Map getNowConfirmPie() {
        Map map = (Map) getDataMap().get("nowConfirmStatis");
        return map;
    }


    public static List<ImportData> getImportList() {
        List<ImportData> result = new ArrayList<>();
        Map map = getH5DataMap();
        ArrayList list = (ArrayList) map.get("areaTree");
        Map subMap = (Map) list.get(0);
        ArrayList children = (ArrayList) subMap.get("children");
        for (int i = 0 ; i < children.size() ; i++) {
            Map provinceMap = (Map) children.get(i);
            //省市名
            String area = (String) provinceMap.get("name");
            //境外输入
            ArrayList childrenList = (ArrayList) provinceMap.get("children");
            for (int j = 0 ; j < childrenList.size() ; j++) {
                Map tmpMap = (Map) childrenList.get(j);
                if ("境外输入".equals(tmpMap.get("name"))) {
                    Map total = (Map) tmpMap.get("total");
                    double importConfirm = (double) total.get("confirm");

                    ImportData importData = new ImportData(area,(int)importConfirm);
                    result.add(importData);
                }
            }
        }
        return result;
    }


    public static List<ImportAddBean> getImportAddList() {
        List<ImportAddBean> result = new ArrayList<>();
        Map map = getDataMap();
        ArrayList chinaDayAddList = (ArrayList) map.get("chinaDayAddList");
        //遍历 list 获取每个子元素中的有用元素
        for (int i = 0 ;i <chinaDayAddList.size() ; i++) {
            Map addMap = (Map) chinaDayAddList.get(i);
            String date = (String) addMap.get("date");
            double importedCase = (double) addMap.get("importedCase");
            ImportAddBean importAddBean = new ImportAddBean(date,(int)importedCase);
            result.add(importAddBean);
        }
        return result;
    }

    public static List<ImportAddBean> getTotalImportList() {
        List<ImportAddBean> result = new ArrayList<>();
        Map map = getDataMap();
        ArrayList chinaDayList = (ArrayList) map.get("chinaDayList");
        for (int i = 0 ; i < chinaDayList.size() ; i++) {
            Map importMap = (Map) chinaDayList.get(i);
            String date = (String) importMap.get("date");
            double importedCase = (double) importMap.get("importedCase");
            ImportAddBean importAddBean = new ImportAddBean(date,(int)importedCase);
            result.add(importAddBean);
        }
        return result;
    }


    public static void main(String[] args) {
        getImportList();
    }




}
