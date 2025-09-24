package dev.flima.portfolio.modules.socialMedia.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialMediaRequestDTO {

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "URL is required.")
    private String url;

    private String description;

}
