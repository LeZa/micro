package com.eureka.build.oauth;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.*;
import org.springframework.security.oauth2.config.annotation.web.configuration.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableOAuth2Client
@EnableAuthorizationServer
@EnableEurekaClient
public class OauthApplication {

	@RequestMapping({ "/user" })
	public Map<String, String> user(Principal principal) {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("name", principal.getName());
		return map;
	}

	public static void main(String[] args) {
		SpringApplication.run(OauthApplication.class, args);
	}

}

