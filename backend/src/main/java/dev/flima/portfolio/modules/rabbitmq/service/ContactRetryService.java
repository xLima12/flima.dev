package dev.flima.portfolio.modules.rabbitmq.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.flima.portfolio.modules.message.dto.ContactMessageDTO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactRetryService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void retryOneMessage() {
        Message message = this.rabbitTemplate.receive("contact.queue.dlq");

        if(message == null) {
            System.out.println("Nenhuma mensagem disponivel na DLQ");
            return;
        }

        try {
            ContactMessageDTO dto = this.objectMapper.readValue(message.getBody(), ContactMessageDTO.class);

            this.rabbitTemplate.convertAndSend("contact.exchange", "contact.routingKey", dto);

            System.out.println("Mensagem reenviada com sucesso para a contact.queue");
        } catch (Exception e) {
            System.err.println("Erro ao reprocessar mensagem da DLQ: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
