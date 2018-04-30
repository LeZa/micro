package serviceserver1.serviceserver1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceServer1Application {

	public static void main(String[] args) {
		SpringApplication.run(ServiceServer1Application.class, args);
	}
}
