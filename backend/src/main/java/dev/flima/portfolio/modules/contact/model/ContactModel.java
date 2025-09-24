package dev.flima.portfolio.modules.contact.model;

import dev.flima.portfolio.modules.contact.enums.TypeContact;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_contact")
public class ContactModel {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;

    @NotBlank(message = "Title is required.")
    private String title;

    @NotNull(message = "Type contact is required.")
    private TypeContact typeContact;

    @NotBlank(message = "Content is required.")
    private String content;

    private String description;

}
