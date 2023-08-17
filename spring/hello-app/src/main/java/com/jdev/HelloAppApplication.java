package com.jdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hello.service.ConsoleHelloService;
import hello.service.HelloService;

@SpringBootApplication
public class HelloAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloAppApplication.class, args);
	}
	
	@Bean
	public HelloService helloService() {
		return new ConsoleHelloService("Howdy", "!"); 
	}

}
