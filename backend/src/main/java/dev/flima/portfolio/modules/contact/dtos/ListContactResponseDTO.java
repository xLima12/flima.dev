package dev.flima.portfolio.modules.contact.dtos;

import dev.flima.portfolio.modules.contact.enums.TypeContact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListContactResponseDTO {

    private UUID id;

    private String title;

    private TypeContact typeContact;

    private String content;

    private String description;

}
