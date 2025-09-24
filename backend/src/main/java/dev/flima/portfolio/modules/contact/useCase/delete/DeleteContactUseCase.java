package dev.flima.portfolio.modules.contact.useCase.delete;

import dev.flima.portfolio.modules.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteContactUseCase {

    @Autowired
    private ContactService contactService;

    public boolean execute(UUID id) {
        return contactService.deleteContact(id);
    }

}
