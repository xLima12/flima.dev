package dev.flima.portfolio.modules.socialMedia.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListSocialMediaResponseDTO {

    private UUID id;
    private String name;
    private String url;
    private String description;

}
