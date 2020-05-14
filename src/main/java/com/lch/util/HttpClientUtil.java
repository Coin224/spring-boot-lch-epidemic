package com.lch.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {




    public static String doGet(String urlStr) {

        //提供了闭合的HttpClient对象
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = null;

        try {
            //使用默认的创建方式
            httpClient = HttpClients.createDefault();
            //创建一个get请求 传入url
            HttpGet httpGet = new HttpGet(urlStr);
            //设置请求头的方式 选择要接收的参数类型
            httpGet.addHeader("Accept", "application/json");

            // 设置请求参数
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(35000)//设置连接时间
                    .setConnectionRequestTimeout(35000)//从共享连接池中取出连接的超时时间
                    .setSocketTimeout(60000)//数据读取时间
                    .build();
            //设置配置参数
            httpGet.setConfig(requestConfig);

            //执行请求
            httpResponse = httpClient.execute(httpGet);

            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) {
        String url = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_other";
        String result = doGet(url);
        System.out.println(result);
    }
}
