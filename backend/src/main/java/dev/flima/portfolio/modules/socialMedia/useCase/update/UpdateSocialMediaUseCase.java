package dev.flima.portfolio.modules.socialMedia.useCase.update;

import dev.flima.portfolio.modules.socialMedia.dtos.SocialMediaRequestDTO;
import dev.flima.portfolio.modules.socialMedia.dtos.SocialMediaResponseDTO;
import dev.flima.portfolio.modules.socialMedia.service.SocialMediaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/social-media")
public class UpdateSocialMediaUseCase {

    @Autowired
    private SocialMediaService socialMediaService;

    @PutMapping("/{id}")
    public ResponseEntity<SocialMediaResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid SocialMediaRequestDTO requestDTO) {
        return this.socialMediaService.updatedSocialMedia(id, requestDTO);
    }

}
