package com.composite.compositeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CompositeserviceApplication {
		public static void main(String[] args) {
		SpringApplication.run(CompositeserviceApplication.class, args);
	}
}