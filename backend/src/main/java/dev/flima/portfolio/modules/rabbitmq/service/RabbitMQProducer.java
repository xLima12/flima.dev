package dev.flima.portfolio.modules.rabbitmq.service;

import dev.flima.portfolio.config.RabbitMQConfig;
import dev.flima.portfolio.modules.message.dto.ContactMessageDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendContactMessage(ContactMessageDTO messageDTO) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.CONTACT_EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                messageDTO
        );
    }
}
