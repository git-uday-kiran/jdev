package com.jdev.beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicInteger;

@Log4j2
@Configuration
public class BeanTest {

    public static final AtomicInteger count = new AtomicInteger(0);

    public int number;
    public String name = "bean-test";

    public BeanTest() {
        number = count.incrementAndGet();
        log.info("Creating the BeanTest Object {}, count {}", this, number);
    }

    @PostConstruct
    public void init() {
        log.warn("BeanTest is initialized {}", this);
    }

    @PreDestroy
    public void destroy() {
        log.info("BeanTest, pre-destroyed is called {}", this);
    }

    @Override
    public String toString() {
        return "BeanTest{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}

