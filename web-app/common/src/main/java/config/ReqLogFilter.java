package config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ReqLogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        Map<String, String> req = new HashMap<>();
        req.put("req.remoteHost", servletRequest.getRemoteHost());

        if (servletRequest instanceof HttpServletRequest httpServletRequest) {
            req.put("req.requestURI", httpServletRequest.getRequestURI());
            StringBuffer requestURL = httpServletRequest.getRequestURL();
            if (requestURL != null) {
                req.put("req.requestURL", requestURL.toString());
            }
            req.put("req.method", httpServletRequest.getMethod());
            req.put("req.req.queryString", httpServletRequest.getQueryString());
            req.put("req.userAgent", httpServletRequest.getHeader("User-Agent"));
            req.put("req.xForwardedFor", httpServletRequest.getHeader("X-Forwarded-For"));
//            req.put("req.x-b3-sampled", httpServletRequest.getHeader("x-b3-sampled"));
//            req.put("req.x-b3-parentspanid", httpServletRequest.getHeader("x-b3-parentspanid"));
            req.put("req.x-b3-spanid", httpServletRequest.getHeader("x-b3-spanid"));
            req.put("req.x-b3-traceid", httpServletRequest.getHeader("x-b3-traceid"));
        }

        log.trace("Received request {} ", req);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
