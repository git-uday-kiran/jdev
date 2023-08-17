package com.jdev.beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class LifeCycleTest implements SmartLifecycle {

    /**
     * will not be called if it implements any Bean LifeCycle interface
     */
    @PostConstruct
    public void init() {
        log.info("LifeCycleTest, post construct is called");
    }

    /**
     * will not be called if it implements any Bean LifeCycle interface
     */
    @PreDestroy
    public void destroy() {
        log.info("LifeCycleTest, pre destroy is called");
    }

    @Override
    public void start() {
        log.info("LifeCycleTest is started...");
    }

    @Override
    public void stop() {
        log.info("LifeCycleTest is stopped...");
    }

    @Override
    public boolean isRunning() {
        log.info("LifeCycleTest checking isRunning...");
        return false;
    }
}
