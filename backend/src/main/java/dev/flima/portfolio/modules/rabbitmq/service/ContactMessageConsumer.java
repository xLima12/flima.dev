package dev.flima.portfolio.modules.rabbitmq.service;

import dev.flima.portfolio.config.RabbitMQConfig;
import dev.flima.portfolio.modules.message.dto.ContactMessageDTO;
import dev.flima.portfolio.modules.message.service.ContactMessageMetricsService;
import dev.flima.portfolio.modules.message.service.ContactMessageService;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactMessageConsumer {

    private static final Logger log = LoggerFactory.getLogger(ContactMessageConsumer.class);
    @Autowired
    private ContactMessageService messageService;

    @Autowired
    private ContactMessageMetricsService metrics;

    @Timed(value = "contact.message.process.time", description = "Tempo de processamento da mensagem de contato")
    @RabbitListener(queues = RabbitMQConfig.CONTACT_QUEUE, containerFactory = "rabbitListenerContainerFactory")
    public void receive(ContactMessageDTO messageDTO) {
        try {
            metrics.recordProcessing(() -> {
                this.messageService.sendContactMessage(messageDTO);
                metrics.incrementSuccess();
            });
        } catch (Exception e) {
            metrics.incrementError();
            log.error("Erro ao processar a mensagem", e);
            throw new RuntimeException(e);
        }
    }

//    @RabbitListener(queues = RabbitMQConfig.CONTACT_DLQ)
//    public void handleDlq(ContactMessageDTO messageDTO) {
//        log.error("Mensagem enviada para DLQ: {}", messageDTO);
//        // Aqui você pode persistir erro, alertar, etc.
//    }


}
