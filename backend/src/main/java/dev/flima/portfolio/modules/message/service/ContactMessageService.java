package dev.flima.portfolio.modules.message.service;

import dev.flima.portfolio.modules.message.dto.ContactMessageDTO;
import dev.flima.portfolio.modules.message.model.ContactMessage;
import dev.flima.portfolio.modules.message.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ContactMessageService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ContactMessageRepository contactMessageRepository;

    public void sendContactMessage(ContactMessageDTO messageDTO) {
        ContactMessage contactMessage = new ContactMessage();
        contactMessage.setName(messageDTO.getName());
        contactMessage.setEmail(messageDTO.getEmail());
        contactMessage.setSubject(messageDTO.getSubject());
        contactMessage.setMessage(messageDTO.getMessage());

        this.contactMessageRepository.save(contactMessage);

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(messageDTO.getEmail());
        mail.setFrom(from);
        mail.setSubject("Novo contato recebido: " + messageDTO.getSubject());
        mail.setText("Nome: " + messageDTO.getName() + "\n"
                + "E-mail: " + messageDTO.getEmail() + "\n"
                + "Mensagem: " + messageDTO.getMessage()
        );

        mailSender.send(mail);
    }

}
