package com.jdev;

import static org.hamcrest.CoreMatchers.containsString;

import org.junit.After;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.service.HelloService;

@SpringBootTest
class HelloAutoConfigApplicationTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Rule
	public OutputCaptureRule output = new OutputCaptureRule();

	private ConfigurableApplicationContext context;

	@After
	public void closeContext() {
		if (this.context != null) {
			this.context.close();
		}
	}

	@Test
	public void defaultServiceIsAutoConfigured() {
		// load
		HelloService service = this.context.getBean(HelloService.class);
		service.sayHello("World");
		this.output.expect(containsString("Hello World!"));
	}
	
	public  void load(Class<?> config, String... environment) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(config);
		TestPropertyValues.of(environment).applyTo(ctx);
		ctx.refresh();
		this.context = ctx;
	}

	@Test
	void contextLoads() {
	}

}
