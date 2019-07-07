package com.pky.hello.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "com.pky.hello.springboot") //将所有基于该包名进行扫描
@MapperScan(basePackages = "com.pky.hello.springboot.commons.mapper")  //指定扫描 mapper，避免扫描到 tk.mybatis.mapper 包的接口
public class HelloSpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloSpringbootApplication.class, args);
    }
}
