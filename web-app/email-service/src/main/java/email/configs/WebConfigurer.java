package email.configs;

import config.ReqLogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class WebConfigurer {

    @Bean
    public FilterRegistrationBean<ReqLogFilter> beanFilterRegistrationBean(ReqLogFilter reqLogFilter) {
        FilterRegistrationBean<ReqLogFilter> register = new FilterRegistrationBean<>();
        register.setFilter(reqLogFilter);
        register.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return register;
    }

    @Bean
    public ReqLogFilter getReqLogFilter() {
        return new ReqLogFilter();
    }

}
