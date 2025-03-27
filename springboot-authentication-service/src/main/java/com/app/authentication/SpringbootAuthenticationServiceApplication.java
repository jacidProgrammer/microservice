package com.app.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootAuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAuthenticationServiceApplication.class, args);
	}

}
