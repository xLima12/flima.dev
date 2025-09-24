package dev.flima.portfolio.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    public static final String CONTACT_QUEUE = "contact.queue";
    public static final String CONTACT_DLQ = "contact.queue.dlq";
    public static final String CONTACT_EXCHANGE = "contact.exchange";
    public static final String CONTACT_EXCHANGE_DLQ = "contact.exchange.dlq";
    public static final String ROUTING_KEY = "contact.routingKey";

    @Bean
    public Queue queue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", CONTACT_EXCHANGE_DLQ);
        args.put("x-dead-letter-routing-key", ROUTING_KEY + ".dlq");
        return new Queue(CONTACT_QUEUE, true, false, false, args);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(CONTACT_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public Queue queueDlq() {
        return new Queue(CONTACT_DLQ);
    }

    @Bean
    public DirectExchange exchangeDlq() {
        return new DirectExchange(CONTACT_EXCHANGE_DLQ);
    }

    @Bean
    public Binding bindingDlq(Queue queueDlq, DirectExchange exchangeDlq) {
        return BindingBuilder.bind(queueDlq).to(exchangeDlq).with(ROUTING_KEY + ".dlq");
    }

    @Bean
    public MessageConverter messageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        DefaultClassMapper classMapper = new DefaultClassMapper();

        classMapper.setTrustedPackages("dev.flima.portfolio.modules.message.dto");

        converter.setClassMapper(classMapper);
        return converter;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory, MessageConverter messageConverter
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);

        factory.setAdviceChain(
                RetryInterceptorBuilder.stateless()
                        .maxAttempts(3)
                        .backOffOptions(1000, 2.0, 10000)
                        .recoverer(new RejectAndDontRequeueRecoverer())
                        .build()
        );

        factory.setMessageConverter(messageConverter);
        return factory;
    }

}
