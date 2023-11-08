package com.example.yjm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.example.yjm.dao")
@EnableScheduling
public class MaterialManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaterialManagementApplication.class, args);
    }

}
