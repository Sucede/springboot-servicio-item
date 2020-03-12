package com.sucede.sb.app.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCircuitBreaker
@EnableEurekaClient
//La anotacion de RibbonClient sobra despues de incluir Eureka
//@RibbonClient(name = "products-service")
@EnableFeignClients
@SpringBootApplication
//Con esto deshabilitamos la autoconfiguración del DataSource, ya que no necesitamos aquí JPA
//que lo reconoce por la dependencia de Item entity
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootItemServiceApplication.class, args);
	}

}
