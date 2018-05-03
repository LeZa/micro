package com.eureka.me.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceClient1Application {

	public static void main(String[] args) {
		SpringApplication.run(ServiceClient1Application.class, args);
	}
}
