package com.sucede.sb.app.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCircuitBreaker
@EnableEurekaClient
//La anotacion de RibbonClient sobra despues de incluir Eureka
//@RibbonClient(name = "products-service")
@EnableFeignClients
@SpringBootApplication
public class SpringbootItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootItemServiceApplication.class, args);
	}

}
