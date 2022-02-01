package com.destination.destinationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DestinationApp {

	public static void main(String[] args) {
		SpringApplication.run(DestinationApp.class, args);
	}

}