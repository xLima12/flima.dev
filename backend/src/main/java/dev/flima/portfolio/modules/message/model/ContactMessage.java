package dev.flima.portfolio.modules.message.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_contact_message")
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Email is required.")
    private String email;

    @NotBlank(message = "Subject is required.")
    private String subject;

    @NotBlank(message = "Message is required.")
    private String message;

}
