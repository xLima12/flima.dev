package dev.flima.portfolio.modules.contact.useCase.create;

import dev.flima.portfolio.modules.contact.dtos.ContactRequestDTO;
import dev.flima.portfolio.modules.contact.dtos.ContactResponseDTO;
import dev.flima.portfolio.modules.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateContactUseCase {

    @Autowired
    private ContactService contactService;

    public ContactResponseDTO execute(ContactRequestDTO requestDTO) {
        return contactService.createContact(requestDTO);
    }

}
