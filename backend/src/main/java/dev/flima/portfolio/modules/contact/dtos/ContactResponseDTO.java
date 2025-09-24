package dev.flima.portfolio.modules.contact.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponseDTO {

    private UUID id;
    private String title;

}
