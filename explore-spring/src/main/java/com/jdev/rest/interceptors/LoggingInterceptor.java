package com.jdev.rest.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Log4j2
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");

        joiner.add(combine("method", request.getMethod()));
        joiner.add(combine("url", request.getRequestURL().toString()));
        joiner.add("queries=[" + getParametersAndValuesAsString(request) + "]");

        log.info("Request received with {}", joiner);

        if (notFoundAllHeaders(request, response)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
        log.info("Completed request {}", request.getRequestURL());
    }

    public String combine(String firstString, String secondString) {
        return firstString + "=" + secondString;
    }

    public String getParametersAndValuesAsString(final HttpServletRequest request) {
        final StringJoiner joiner = new StringJoiner(", ");
        final Map<String, String[]> map = request.getParameterMap();

        for (final String key : map.keySet()) {
            final String values = Arrays.stream(map.get(key)).collect(Collectors.joining(", "));
            final String keyValue = key + "=" + (map.get(key).length > 1 ? "[" + values + "]" : values);
            joiner.add(keyValue);
        }

        return joiner.toString();
    }

    public boolean notFoundAllHeaders(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, String... requiredHeaders) {
        final List<String> missingHeaders = Arrays.stream(requiredHeaders).filter(h -> request.getHeader(h) == null).toList();
        try {
            if (missingHeaders.size() > 0) {
                final String msg = "[" + missingHeaders.stream().collect(Collectors.joining(", ")) + "] " + (missingHeaders.size() == 1 ? "is" : "are") + " missing in the request headers.";
                response.getOutputStream().write(msg.getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return missingHeaders.size() > 0;
    }
}
