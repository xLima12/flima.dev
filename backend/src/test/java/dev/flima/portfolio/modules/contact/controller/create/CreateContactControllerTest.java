package dev.flima.portfolio.modules.contact.controller.create;

import dev.flima.portfolio.modules.contact.dtos.ContactRequestDTO;
import dev.flima.portfolio.modules.contact.dtos.ContactResponseDTO;
import dev.flima.portfolio.modules.contact.enums.TypeContact;
import dev.flima.portfolio.modules.contact.useCase.create.CreateContactUseCase;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CreateContactController.class)
@Import(SecurityConfig.class)
@ActiveProfiles("test")
public class CreateContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateContactUseCase useCase;

    @Test
    void shouldReturnCreatedContact() throws Exception {
        ContactResponseDTO responseDTO = new ContactResponseDTO(UUID.randomUUID(), "Meu telefone");
        when(useCase.execute(any())).thenReturn(responseDTO);

        mockMvc.perform(post("/contact/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "title": "Meu telefone",
                            "typeContact": "PHONE",
                            "content": "+99 (99) 99999-9999",
                            "description": "Este é o meu número de telefone para o teste"
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Meu telefone"));
    }

}
