package dev.flima.portfolio.modules.socialMedia.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialMediaResponseDTO {

    private UUID id;
    private String name;

}
