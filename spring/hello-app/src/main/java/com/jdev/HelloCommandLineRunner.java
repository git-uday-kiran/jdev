package com.jdev;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import hello.service.HelloService;


@Component
public class HelloCommandLineRunner implements CommandLineRunner {
	
	private final HelloService helloService;
	
	public HelloCommandLineRunner(HelloService helloService) {
		this.helloService = helloService;
	}

	@Override
	public void run(String... args) throws Exception {
		this.helloService.sayHello("World");
	}

}
