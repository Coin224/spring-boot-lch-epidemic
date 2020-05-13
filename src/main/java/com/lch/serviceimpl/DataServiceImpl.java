package com.lch.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lch.bean.DataBean;
import com.lch.handler.DataHandler;
import com.lch.handler.JsoupHandler;
import com.lch.mapper.DataMapper;
import com.lch.service.DataService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DataServiceImpl extends ServiceImpl<DataMapper,DataBean>
        implements DataService {

//    @Override
//    public List<DataBean> getData() {
//        try {
//            // 调用DataHandler中的静态方法获取数据。
//            //return DataHandler.getData();
//            return JsoupHandler.getData();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public List<DataBean> listById(int id) {
//        try {
//        if (id == 1) {
//            return JsoupHandler.getData();
//        } else if (id == 2) {
//            return DataHandler.getData();
//        } } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
