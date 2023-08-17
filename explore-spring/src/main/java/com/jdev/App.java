package com.jdev;

import com.jdev.configs.DBConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.atomic.AtomicInteger;

@Log4j2
@EnableConfigurationProperties
@SpringBootApplication
public class App {

    public static AtomicInteger count = new AtomicInteger(0);

    public static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        System.out.println("Hello World! Count: " + count.incrementAndGet());

        System.out.println("--------------------------------------------");
        System.out.println("Args:");
        for (String arg : args) {
            System.out.println(arg);
        }
        System.out.println("--------------------------------------------");

        SpringApplication application = new SpringApplication(App.class);
        application.setBannerMode(Banner.Mode.CONSOLE);

        context = application.run(args);
        startApp(context);

    }

    public static void startApp(ApplicationContext ctx) {
        System.out.println("Hey! there, App is started :)");
        System.out.println("Started..............");

        DBConfig config = ctx.getBean(DBConfig.class);

        System.out.println(config);
    }

}
