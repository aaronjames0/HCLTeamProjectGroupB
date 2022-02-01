package com.example.Recommendmicroservice;

//import com.example.Recommendmicroservice.Entity.Recommend;
//import com.example.Recommendmicroservice.Repository.RecommendRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RecommendMicroserviceApplication {

	public static void main(String[] args) {

		//ConfigurableApplicationContext configurableApplicationContext = 
		SpringApplication.run(RecommendMicroserviceApplication.class, args);
		//RecommendRepo recommendRepo = configurableApplicationContext.getBean(RecommendRepo.class,args);
		//recommendRepo.save(new Recommend());
	}


}
