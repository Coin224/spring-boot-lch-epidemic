package com.lch.serviceimpl;

import com.lch.bean.DataBean;
import com.lch.handler.DataHandler;
import com.lch.service.DataService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class DataServiceImpl implements DataService {

    @Override
    public Map<String, Object> getData() {
        try {
            // 调用DataHandler中的静态方法获取数据。
            return DataHandler.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
