package com.lch.handler;

import com.google.gson.Gson;
import com.lch.bean.GraphBean;
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


    public static List<GraphBean> getGraphData () {
        List<GraphBean> result = new ArrayList<>();

        //用Util中的工具来获得数据
        String str = HttpClientUtil.doGet(url);

        // 通过Gson来解析数据
        Gson gson = new Gson();
        Map map = gson.fromJson(str, Map.class);

        //获取map中的data
        String data = (String) map.get("data");
        Map subMap = gson.fromJson(data, Map.class);
        System.out.println(subMap);
        ArrayList list = (ArrayList) subMap.get("chinaDayList");
        //遍历list
        for (int i = 0; i < list.size() ; i++) {
            Map graphMap = (Map) list.get(i);
            String date = (String) graphMap.get("date");
            double nowConfirm = (double) graphMap.get("nowConfirm");
            GraphBean graphBean = new GraphBean(date,(int)nowConfirm);
            result.add(graphBean);
        }
        System.out.println(result);
        return result;

    }

    public static void main(String[] args) {
        getGraphData();
    }

}
