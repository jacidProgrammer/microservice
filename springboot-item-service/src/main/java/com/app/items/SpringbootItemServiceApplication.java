package com.app.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// Not needed because eureka do it @RibbonClient(name = "product-service")
@EnableCircuitBreaker
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class SpringbootItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootItemServiceApplication.class, args);
	}

}
