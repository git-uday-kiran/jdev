package content_checker.service;

import jakarta.jms.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;

import java.beans.BeanProperty;

@Component
@EnableJms
public class JmsConfig {

    @Bean
    public DefaultJmsListenerContainerFactory myFactory(@Qualifier("jmsConnectionFactory") ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer factoryConfigurer) {
        var factory = new DefaultJmsListenerContainerFactory();
        factoryConfigurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public MessageConverter jaksonJmsMessageConverter() {
        var converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_text");
        return converter;
    }


}

