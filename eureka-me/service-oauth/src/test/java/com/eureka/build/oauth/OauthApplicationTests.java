package com.eureka.build.oauth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.test.context.junit4.SpringRunner;

public class OauthApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testPass(){
		String pass = "123456";
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();

		String hashPass = encode.encode(pass);
		System.out.println(hashPass);
	}

}
