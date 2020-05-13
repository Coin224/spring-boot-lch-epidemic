package com.lch.handler;

import com.google.gson.Gson;
import com.lch.bean.DataBean;
import com.lch.util.HttpURLConnectionUtil;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这是一个数据处理类
 */
public class DataHandler {


    //public static String name = "{\"name\":\"李春宏\"}";

    private static String urlStr = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";


    // 处理爬取的JSON数据
    public static List<DataBean> getData() throws Exception{
        //创建Gson对象的两种方法
        //Gson gson = new Gson();
        //Gson gson1 = new GsonBuilder().create();
        //Map map = gson.fromJson(name, Map.class);
        //System.out.println(map);



//        //先读取文件中的内容 然后转化为JAVA对象
//        FileReader fileReader = new FileReader("tmp.txt");
//        char[] cbuf = new char[1024];
//        int count = 0;
//        StringBuilder builder = new StringBuilder();
//        while ((count = fileReader.read(cbuf)) != -1) {
//            builder.append(new String(cbuf,0,count));
//        }
//        fileReader.close();
//        //System.out.println(builder.toString());

        //Map<String,Object> resultMap = new HashMap<>();

        //从网页中获取实时数据  JSON
        String respJson = HttpURLConnectionUtil.getNetData(urlStr);
        Gson gson = new Gson();
        Map map = gson.fromJson(respJson,Map.class);
        String mapJson = (String) map.get("data");
        Map map1 = gson.fromJson(mapJson,Map.class);
        //获取最后更新时间
        //String lastUpdateTime = (String) map1.get("lastUpdateTime");
        //resultMap.put("lastUpdateTime",lastUpdateTime);

        //转化为java对象
        //Map map = gson.fromJson(builder.toString(),Map.class);
        //System.out.println(map);
        ArrayList areaTree = (ArrayList) map1.get("areaTree");
        Map treeMap = (Map) areaTree.get(0);
        ArrayList childrenList = (ArrayList) treeMap.get("children");
        List<DataBean> resultList = new ArrayList<>();
        //遍历然后转化
        for (int i = 0 ; i < childrenList.size() ; i++) {
            Map childrenMap = (Map) childrenList.get(i);
            String name = (String) childrenMap.get("name");
            Map totalMap = (Map) childrenMap.get("total");
            double nowConfirm = (double) totalMap.get("nowConfirm");
            double confirm = (double) totalMap.get("confirm");
            double heal = (double) totalMap.get("heal");
            double dead = (double) totalMap.get("dead");
            DataBean dataBean = new DataBean(name,(int)nowConfirm,(int)confirm,(int)heal,(int)dead);
            resultList.add(dataBean);
        }
        //resultMap.put("resultList",resultList);
        //System.out.println(resultMap);
        return resultList;
    }

//    public static void main(String[] args) throws Exception {
//        getData();
//    }
}
