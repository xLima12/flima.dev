package dev.flima.portfolio.modules.faq.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListFaqResponseDTO {

    private UUID id;

    private String question;

    private String answer;

}
