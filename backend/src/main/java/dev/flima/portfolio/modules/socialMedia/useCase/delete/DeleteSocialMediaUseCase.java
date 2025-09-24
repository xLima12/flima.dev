package dev.flima.portfolio.modules.socialMedia.useCase.delete;

import dev.flima.portfolio.modules.socialMedia.service.SocialMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/social-media")
public class DeleteSocialMediaUseCase {

    @Autowired
    private SocialMediaService socialMediaService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return this.socialMediaService.deleteSocialMedia(id);
    }

}
