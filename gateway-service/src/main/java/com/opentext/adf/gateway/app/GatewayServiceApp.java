package com.opentext.adf.gateway.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Gateway service application.jd
 *
 * @author opentext
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.opentext.adf.*"})
public class GatewayServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApp.class, args);
    }
}
