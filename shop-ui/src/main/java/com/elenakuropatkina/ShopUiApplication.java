package com.elenakuropatkina;

import com.netflix.discovery.EurekaNamespace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ShopUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopUiApplication.class, args);
    }

}
