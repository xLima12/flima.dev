package dev.flima.portfolio.modules.contact.controller.list;

import dev.flima.portfolio.modules.contact.dtos.ListContactResponseDTO;
import dev.flima.portfolio.modules.contact.enums.TypeContact;
import dev.flima.portfolio.modules.contact.useCase.list.ListContactUseCase;
import dev.flima.portfolio.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@WebMvcTest(ListContactController.class)
@Import(SecurityConfig.class)
@ActiveProfiles("test")
public class ListContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ListContactUseCase useCase;

    @Test
    void shouldReturnContactList() throws Exception {
        UUID idOne = UUID.randomUUID();
        UUID idTwo = UUID.randomUUID();

        ListContactResponseDTO contactOne = new ListContactResponseDTO(
                idOne, "Contact One", TypeContact.EMAIL, "contact@one.com", "Test Contact One");

        ListContactResponseDTO contactTwo = new ListContactResponseDTO(
                idTwo, "Contact Two", TypeContact.PHONE, "+1 1 111-111", "Test Contact One");

        List<ListContactResponseDTO> listContact = List.of(contactOne, contactTwo);

        when(useCase.execute()).thenReturn(listContact);

        mockMvc.perform(get("/contact/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].content").value("contact@one.com"))
                .andExpect(jsonPath("$[1].content").value("+1 1 111-111"))
                .andExpect(jsonPath("$[0].typeContact").value("EMAIL"))
                .andExpect(jsonPath("$[1].typeContact").value("PHONE"));

    }
}
