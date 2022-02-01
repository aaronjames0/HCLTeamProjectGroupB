package com.example.Recommendmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RecommendMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecommendMicroserviceApplication.class, args);
	}
}