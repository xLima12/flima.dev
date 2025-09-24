package dev.flima.portfolio.modules.socialMedia.useCase.create;

import dev.flima.portfolio.modules.socialMedia.dtos.SocialMediaRequestDTO;
import dev.flima.portfolio.modules.socialMedia.dtos.SocialMediaResponseDTO;
import dev.flima.portfolio.modules.socialMedia.service.SocialMediaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/social-media")
public class CreateSocialMediaUseCase {

    @Autowired
    private SocialMediaService socialMediaService;

    @PostMapping("/")
    public ResponseEntity<SocialMediaResponseDTO> create(@RequestBody @Valid SocialMediaRequestDTO requestDTO) {
        return this.socialMediaService.createSocialMedia(requestDTO);
    }

}
