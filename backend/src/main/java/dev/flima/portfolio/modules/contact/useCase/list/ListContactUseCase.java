package dev.flima.portfolio.modules.contact.useCase.list;

import dev.flima.portfolio.modules.contact.dtos.ListContactResponseDTO;
import dev.flima.portfolio.modules.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListContactUseCase {

    @Autowired
    private ContactService contactService;

    public List<ListContactResponseDTO> execute() {
        return contactService.listContact();
    }

}
