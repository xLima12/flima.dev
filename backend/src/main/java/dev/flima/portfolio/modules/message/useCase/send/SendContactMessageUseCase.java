package dev.flima.portfolio.modules.message.useCase.send;

import dev.flima.portfolio.modules.message.dto.ContactMessageDTO;
import dev.flima.portfolio.modules.rabbitmq.service.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class SendContactMessageUseCase {

    @Autowired
    private RabbitMQProducer producer;

    @PostMapping("/message")
    public ResponseEntity<Void> send(@RequestBody ContactMessageDTO messageDTO) {
        this.producer.sendContactMessage(messageDTO);
        return ResponseEntity.accepted().build();
    }

}
