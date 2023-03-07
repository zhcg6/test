package com.person.stuservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.person"})
public class StuApplication {
    public static void main(String[] args) {
        SpringApplication.run(StuApplication.class,args);
    }
}
