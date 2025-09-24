package dev.flima.portfolio.modules.message.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactMessageDTO implements Serializable {

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Email is required.")
    private String email;

    @NotBlank(message = "Subject is required.")
    private String subject;

    @NotBlank(message = "Message is required.")
    private String message;
}
