package dev.flima.portfolio.modules.contact.usecase.update;

import dev.flima.portfolio.modules.contact.dtos.ContactRequestDTO;
import dev.flima.portfolio.modules.contact.dtos.ContactResponseDTO;
import dev.flima.portfolio.modules.contact.enums.TypeContact;
import dev.flima.portfolio.modules.contact.service.ContactService;
import dev.flima.portfolio.modules.contact.useCase.update.UpdateContactUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateContactUseCaseTest {

    @Mock
    private ContactService contactService;

    @InjectMocks
    private UpdateContactUseCase useCase;

    @Test
    void shouldCallServiceAndReturnContactUpdate() {
        UUID id = UUID.randomUUID();
        ContactRequestDTO requestDTO = new ContactRequestDTO(
                "E-mail", TypeContact.EMAIL, "test@email.com", "E-mail test");
        ContactResponseDTO responseDTO = new ContactResponseDTO(id, requestDTO.getTitle());

        when(contactService.updateContact(eq(id), eq(requestDTO))).thenReturn(responseDTO);

        ContactResponseDTO result = useCase.execute(id, requestDTO);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(contactService).updateContact(id, requestDTO);
    }

}
