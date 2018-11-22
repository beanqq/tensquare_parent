package com.tensquare.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * Created by Administrator on 2018/11/13.
 */
@SpringBootApplication
public class BaseApplication {
    public static void main(String[] args) {

        SpringApplication.run(BaseApplication.class,args);
    }


    @Bean  //注入到spring容器中
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
