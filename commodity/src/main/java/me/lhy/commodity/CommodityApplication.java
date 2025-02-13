package me.lhy.commodity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"me.lhy.commons"})
@EnableDiscoveryClient // 开启服务注册与发现
@MapperScan("me.lhy.commodity.mapper")
public class CommodityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommodityApplication.class, args);
    }

}
