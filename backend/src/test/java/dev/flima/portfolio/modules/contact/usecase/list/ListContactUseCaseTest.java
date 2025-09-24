package dev.flima.portfolio.modules.contact.usecase.list;

import dev.flima.portfolio.modules.contact.dtos.ListContactResponseDTO;
import dev.flima.portfolio.modules.contact.enums.TypeContact;
import dev.flima.portfolio.modules.contact.service.ContactService;
import dev.flima.portfolio.modules.contact.useCase.list.ListContactUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ListContactUseCaseTest {

    @Mock
    private ContactService contactService;

    @InjectMocks
    private ListContactUseCase useCase;

    @Test
    void shouldCallServiceAndReturnListContact() {
        UUID idOne = UUID.randomUUID();
        UUID idTwo = UUID.randomUUID();

        ListContactResponseDTO contactOne = new ListContactResponseDTO(
                idOne, "Contact One", TypeContact.EMAIL, "contact@one.com", "Test Contact One");

        ListContactResponseDTO contactTwo = new ListContactResponseDTO(
                idTwo, "Contact Two", TypeContact.PHONE, "+1 1 111-111", "Test Contact One");

        List<ListContactResponseDTO> listResponse = List.of(contactOne, contactTwo);

        when(contactService.listContact()).thenReturn(listResponse);

        List<ListContactResponseDTO> result = useCase.execute();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(contactService).listContact();
        verifyNoMoreInteractions(contactService);
    }

}
