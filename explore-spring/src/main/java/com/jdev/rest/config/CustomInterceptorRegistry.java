package com.jdev.rest.config;

import com.jdev.rest.interceptors.LoggingInterceptor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Log4j2
@Configuration
public class CustomInterceptorRegistry implements WebMvcConfigurer {

    private final LoggingInterceptor loggingInterceptor;

    public CustomInterceptorRegistry(LoggingInterceptor loggingInterceptor) {
        this.loggingInterceptor = loggingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("Adding InterprotrRegistry {}", registry);
        registry.addInterceptor(loggingInterceptor);
    }
}
