package dev.flima.portfolio.modules.contact.controller.delete;

import dev.flima.portfolio.modules.contact.useCase.delete.DeleteContactUseCase;
import dev.flima.portfolio.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

@WebMvcTest(DeleteContactController.class)
@Import(SecurityConfig.class)
@ActiveProfiles("test")
public class DeleteContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DeleteContactUseCase useCase;

    @Test
    void shouldDeleteContact() throws Exception {
        UUID id = UUID.randomUUID();

        when(useCase.execute(id)).thenReturn(Boolean.TRUE);

        mockMvc.perform(delete("/contact/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(useCase).execute(id);
        verifyNoMoreInteractions(useCase);
    }
}
