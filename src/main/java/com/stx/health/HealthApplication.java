package com.stx.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HealthApplication {

    static {
        System.setProperty("druid.mysql.usePingMethod", "false");
    }

    public static void main(String[] args) {
        SpringApplication.run(HealthApplication.class, args);
    }

}
