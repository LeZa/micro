package com.eureke.me.server;

import com.netflix.eureka.cluster.PeerEurekaNodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceServerApplication.class, args);
	}
}
