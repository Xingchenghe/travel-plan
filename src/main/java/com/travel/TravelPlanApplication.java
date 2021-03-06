package com.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.travel.mapper")
public class TravelPlanApplication {
    public static void main(String[] args) {
        SpringApplication.run(TravelPlanApplication.class);
    }
}
