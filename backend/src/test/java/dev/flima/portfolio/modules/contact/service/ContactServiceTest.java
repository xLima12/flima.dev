package dev.flima.portfolio.modules.contact.service;

import dev.flima.portfolio.modules.contact.dtos.ContactRequestDTO;
import dev.flima.portfolio.modules.contact.dtos.ContactResponseDTO;
import dev.flima.portfolio.modules.contact.dtos.ListContactResponseDTO;
import dev.flima.portfolio.modules.contact.enums.TypeContact;
import dev.flima.portfolio.modules.contact.model.ContactModel;
import dev.flima.portfolio.modules.contact.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContactServiceTest {

    @Mock
    private ContactRepository repository;

    @InjectMocks
    private ContactService service;

    @Test
    void shouldCreateContactAndReturnDTO() {
        UUID id = UUID.randomUUID();
        ContactModel contactModel =  new ContactModel(id, "E-mail", TypeContact.EMAIL, "", "");

        when(repository.save(any())).thenReturn(contactModel);

        ContactRequestDTO requestDTO = new ContactRequestDTO("E-mail", TypeContact.EMAIL, "", "");

        ContactResponseDTO result = service.createContact(requestDTO);

        assertEquals("E-mail", result.getTitle());
        verify(repository).save(any());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldUpdateContactAndReturnDTO() {
        UUID id = UUID.randomUUID();
        ContactModel contactModel =  new ContactModel(id, "E-mail", TypeContact.EMAIL, "", "");

        when(repository.save(contactModel)).thenReturn(contactModel);
        when(repository.findById(id)).thenReturn(Optional.of(contactModel));

        ContactRequestDTO requestDTO = new ContactRequestDTO("E-mail Test", TypeContact.EMAIL, "", "");

        ContactResponseDTO result = service.updateContact(id, requestDTO);

        assertNotNull(result);
        assertEquals("E-mail Test", result.getTitle());
        verify(repository).findById(id);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldReturnListContact() {
        UUID idOne = UUID.randomUUID();
        UUID idTwo = UUID.randomUUID();

        ContactModel contactOne = new ContactModel(idOne, "E-mail", TypeContact.EMAIL, "", "");
        ContactModel contactTwo = new ContactModel(idTwo, "Phone", TypeContact.PHONE, "", "");

        List<ContactModel> listContact = List.of(contactOne, contactTwo);

        when(repository.findAll()).thenReturn(listContact);

        List<ListContactResponseDTO> result = service.listContact();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("E-mail", result.getFirst().getTitle());
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldDeleteContact() {
        UUID id = UUID.randomUUID();

        when(repository.existsById(id)).thenReturn(true);
        doNothing().when(repository).deleteById(id);

        boolean result = service.deleteContact(id);

        assertTrue(result);
        verify(repository).deleteById(id);
        verifyNoMoreInteractions(repository);
    }

}
