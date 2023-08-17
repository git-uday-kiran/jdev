package com.jdev.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServrerApplciation{
	
	public static void main(String... args) {
		SpringApplication.run(DiscoveryServrerApplciation.class, args);  
		System.out.println("Hello... Eureka Server!");
	}

}
