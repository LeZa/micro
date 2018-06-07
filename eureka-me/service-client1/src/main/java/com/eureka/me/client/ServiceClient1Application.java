package com.eureka.me.client;

import com.eureka.me.client.config.WrongCodeInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableEurekaClient
public class ServiceClient1Application {


	@Bean
	@LoadBalanced
	RestTemplate newRestTemplate(){
		return  new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceClient1Application.class, args);
	}


/*	@Configuration
	static class TokenWebAdapter
			implements WebMvcConfigurer {

		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(new WrongCodeInterceptor())
					.addPathPatterns("/**");
		}
	}*/
}
