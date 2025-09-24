package dev.flima.portfolio.modules.contact.dtos;

import dev.flima.portfolio.modules.contact.enums.TypeContact;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequestDTO {

    @NotBlank(message = "Title is required.")
    private String title;

    @NotNull(message = "Type contact is required.")
    private TypeContact typeContact;

    @NotBlank(message = "Content is required.")
    private String content;

    private String description;

}
