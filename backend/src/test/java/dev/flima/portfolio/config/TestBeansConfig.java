package dev.flima.portfolio.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class TestBeansConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        return mock(JavaMailSender.class);
    }

    @Bean
    public AmqpTemplate amqpTemplate() {
        return mock(AmqpTemplate.class);
    }

}
