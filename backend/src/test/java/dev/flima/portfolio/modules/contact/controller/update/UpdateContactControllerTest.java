package dev.flima.portfolio.modules.contact.controller.update;

import dev.flima.portfolio.modules.contact.dtos.ContactRequestDTO;
import dev.flima.portfolio.modules.contact.dtos.ContactResponseDTO;
import dev.flima.portfolio.modules.contact.enums.TypeContact;
import dev.flima.portfolio.modules.contact.useCase.update.UpdateContactUseCase;
import dev.flima.portfolio.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UpdateContactController.class)
@Import(SecurityConfig.class)
@ActiveProfiles("test")
public class UpdateContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UpdateContactUseCase useCase;

    @Test
    void shouldReturnUpdateContact() throws Exception {
        UUID id = UUID.randomUUID();

        ContactRequestDTO requestDTO = new ContactRequestDTO(
                "Meu telefone", TypeContact.PHONE,
                "+99 (99) 99999-9999", "Este é o meu número de telefone para o teste");

        ContactResponseDTO responseDTO = new ContactResponseDTO(id, requestDTO.getTitle());

        when(useCase.execute(eq(id), eq(requestDTO))).thenReturn(responseDTO);

        mockMvc.perform(put("/contact/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "title": "Meu telefone",
                            "typeContact": "PHONE",
                            "content": "+99 (99) 99999-9999",
                            "description": "Este é o meu número de telefone para o teste"
                        }
                        """))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.title").value("Meu telefone"));
    }

}
