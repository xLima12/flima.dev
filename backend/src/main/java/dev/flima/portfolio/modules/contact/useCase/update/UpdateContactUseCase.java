package dev.flima.portfolio.modules.contact.useCase.update;

import dev.flima.portfolio.modules.contact.dtos.ContactRequestDTO;
import dev.flima.portfolio.modules.contact.dtos.ContactResponseDTO;
import dev.flima.portfolio.modules.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateContactUseCase {

    @Autowired
    private ContactService contactService;

    public ContactResponseDTO execute(UUID id, ContactRequestDTO requestDTO) {
        return contactService.updateContact(id, requestDTO);
    }

}
