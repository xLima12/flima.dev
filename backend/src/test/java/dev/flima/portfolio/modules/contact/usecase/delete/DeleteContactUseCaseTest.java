package dev.flima.portfolio.modules.contact.usecase.delete;

import dev.flima.portfolio.modules.contact.service.ContactService;
import dev.flima.portfolio.modules.contact.useCase.delete.DeleteContactUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteContactUseCaseTest {

    @Mock
    private ContactService contactService;

    @InjectMocks
    private DeleteContactUseCase useCase;

    @Test
    void shouldCallServiceAndDeleteContact() {
        UUID id = UUID.randomUUID();
        when(contactService.deleteContact(id)).thenReturn(true);

        boolean result = useCase.execute(id);

        assertTrue(result);
        verify(contactService).deleteContact(id);
    }

}
