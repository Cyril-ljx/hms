package com.whp.hms.resource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 19399.
 * @date 2021/2/18.
 * @time 20:23
 */
@MapperScan("com.whp.hms.mapper")
@SpringBootApplication(scanBasePackages = {"com.whp.hms.resource", "com.whp.hms.resource.service"})
public class HmsResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HmsResourceApplication.class, args);
    }
}
