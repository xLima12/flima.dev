package dev.flima.portfolio.modules.socialMedia.service;

import dev.flima.portfolio.modules.socialMedia.dtos.ListSocialMediaResponseDTO;
import dev.flima.portfolio.modules.socialMedia.dtos.SocialMediaRequestDTO;
import dev.flima.portfolio.modules.socialMedia.dtos.SocialMediaResponseDTO;
import dev.flima.portfolio.modules.socialMedia.exception.SocialMediaException;
import dev.flima.portfolio.modules.socialMedia.model.SocialMediaModel;
import dev.flima.portfolio.modules.socialMedia.repository.SocialMediaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Service
public class SocialMediaService {

    @Autowired
    private SocialMediaRepository socialMediaRepository;

    public ResponseEntity<SocialMediaResponseDTO> createSocialMedia(@RequestBody @Valid SocialMediaRequestDTO requestDTO) {
        SocialMediaModel socialMedia = new SocialMediaModel();
        socialMedia.setName(requestDTO.getName());
        socialMedia.setUrl(requestDTO.getUrl());
        socialMedia.setDescription(requestDTO.getDescription());

        SocialMediaModel respCreated = this.socialMediaRepository.save(socialMedia);

        SocialMediaResponseDTO responseDTO = new SocialMediaResponseDTO(respCreated.getId(), respCreated.getName());

        return ResponseEntity.created(URI.create("/social-media/" + responseDTO.getId())).body(responseDTO);
    }

    public ResponseEntity<SocialMediaResponseDTO> updatedSocialMedia(UUID id, SocialMediaRequestDTO requestDTO) {
        SocialMediaModel socialMedia = this.socialMediaRepository.findById(id)
                .orElseThrow(SocialMediaException::new);

        socialMedia.setName(requestDTO.getName());
        socialMedia.setUrl(requestDTO.getUrl());
        socialMedia.setDescription(requestDTO.getDescription());

        SocialMediaModel updated = this.socialMediaRepository.save(socialMedia);

        SocialMediaResponseDTO responseDTO = new SocialMediaResponseDTO(updated.getId(), updated.getName());

        return ResponseEntity.noContent().build();
    }

    public List<ListSocialMediaResponseDTO> listSocialMedia() {
        List<SocialMediaModel> listSocialMedia = this.socialMediaRepository.findAll();
        return listSocialMedia.stream().map(socialMedia -> new ListSocialMediaResponseDTO(
                socialMedia.getId(),
                socialMedia.getName(),
                socialMedia.getUrl(),
                socialMedia.getDescription()
        )).toList();
    }

    public ResponseEntity<Void> deleteSocialMedia(UUID id) {
        if(!this.socialMediaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        this.socialMediaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
