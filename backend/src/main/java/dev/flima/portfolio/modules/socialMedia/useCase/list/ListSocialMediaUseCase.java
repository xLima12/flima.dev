package dev.flima.portfolio.modules.socialMedia.useCase.list;

import dev.flima.portfolio.modules.socialMedia.dtos.ListSocialMediaResponseDTO;
import dev.flima.portfolio.modules.socialMedia.service.SocialMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/social-media")
public class ListSocialMediaUseCase {

    @Autowired
    private SocialMediaService socialMediaService;

    @GetMapping("/")
    public List<ListSocialMediaResponseDTO> list() {
        return this.socialMediaService.listSocialMedia();
    }

}
