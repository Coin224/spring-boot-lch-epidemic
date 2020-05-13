package com.lch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.lch.mapper")
@EnableScheduling//设置定时执行
public class SpringBootLchEpidemicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLchEpidemicApplication.class, args);
    }

}
