package com.eureka.me.token;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceTokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceTokenApplication.class, args);
	}
}
