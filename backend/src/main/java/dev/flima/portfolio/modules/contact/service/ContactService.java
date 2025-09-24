package dev.flima.portfolio.modules.contact.service;

import dev.flima.portfolio.modules.contact.dtos.ContactRequestDTO;
import dev.flima.portfolio.modules.contact.dtos.ContactResponseDTO;
import dev.flima.portfolio.modules.contact.dtos.ListContactResponseDTO;
import dev.flima.portfolio.modules.contact.exception.ContactNotFoundException;
import dev.flima.portfolio.modules.contact.model.ContactModel;
import dev.flima.portfolio.modules.contact.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public ContactResponseDTO createContact(ContactRequestDTO requestDTO) {
        ContactModel contactModel = new ContactModel();

        contactModel.setTitle(requestDTO.getTitle());
        contactModel.setTypeContact(requestDTO.getTypeContact());
        contactModel.setContent(requestDTO.getContent());
        contactModel.setDescription(requestDTO.getDescription());

        ContactModel respCreated = this.contactRepository.save(contactModel);

        return new ContactResponseDTO(respCreated.getId(), respCreated.getTitle());
    }

    public List<ListContactResponseDTO> listContact() {
        List<ContactModel> listContact = this.contactRepository.findAll();

        return listContact.stream().map(contact -> new ListContactResponseDTO(
                contact.getId(),
                contact.getTitle(),
                contact.getTypeContact(),
                contact.getContent(),
                contact.getDescription()
        )).toList();
    }

    public ContactResponseDTO updateContact(UUID id, ContactRequestDTO requestDTO) {
        ContactModel contact = this.contactRepository.findById(id)
                .orElseThrow(ContactNotFoundException::new);

        contact.setTitle(requestDTO.getTitle());
        contact.setTypeContact(requestDTO.getTypeContact());
        contact.setContent(requestDTO.getContent());
        contact.setDescription(requestDTO.getDescription());

        ContactModel updated = this.contactRepository.save(contact);

        return new ContactResponseDTO(updated.getId(), updated.getTitle());
    }

    public boolean deleteContact(UUID id) {
        if(!this.contactRepository.existsById(id)) {
            return false;
        }

        this.contactRepository.deleteById(id);
        return true;
    }

}
