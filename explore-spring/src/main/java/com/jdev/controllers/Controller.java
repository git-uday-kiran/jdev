package com.jdev.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class Controller {

    public final ConfigurableApplicationContext context;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getIt() {
        return "Hey, It's Working...";
    }

    @GetMapping(value = {"close", "shutdown", "exit"})
    @ResponseStatus(HttpStatus.OK)
    public String shutDown() {
        exit(3, TimeUnit.SECONDS);
        return "Shutting down...";
    }

    public void exit(long time, TimeUnit timeUnit) {
        CompletableFuture.runAsync(() -> {
            try {
                long millis = timeUnit.toMillis(time);
                log.info("Shutting down in {} milliseconds", millis);
                Thread.sleep(millis);
                context.close();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
