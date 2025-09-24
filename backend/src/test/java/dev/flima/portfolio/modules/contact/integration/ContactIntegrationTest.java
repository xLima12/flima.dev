package dev.flima.portfolio.modules.contact.integration;

import dev.flima.portfolio.config.TestBeansConfig;
import dev.flima.portfolio.modules.contact.dtos.ContactRequestDTO;
import dev.flima.portfolio.modules.contact.enums.TypeContact;
import dev.flima.portfolio.modules.contact.model.ContactModel;
import dev.flima.portfolio.modules.contact.repository.ContactRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@Import(TestBeansConfig.class)
public class ContactIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateAndListContacts() throws Exception {
        ContactRequestDTO requestDTO = new ContactRequestDTO(
                "Contact One",
                TypeContact.EMAIL,
                "contact@one.com",
                "First contact test"
        );

        mockMvc.perform(post("/contact/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated());

        List<ContactModel> contactsInDb = contactRepository.findAll();
        assertEquals(1, contactsInDb.size());
        assertEquals("contact@one.com", contactsInDb.getFirst().getContent());

        mockMvc.perform(get("/contact/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Contact One"));
    }

    @Test
    void shouldDeleteContact() throws Exception {
        ContactModel contactModel = new ContactModel();
        contactModel.setTitle("Contact Two");
        contactModel.setTypeContact(TypeContact.PHONE);
        contactModel.setContent("+55 11 99999-9999");
        contactModel.setDescription("Second Contact");

        contactRepository.saveAndFlush(contactModel);

        mockMvc.perform(delete("/contact/{id}", contactModel.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        assertFalse(contactRepository.existsById(contactModel.getId()));
    }
}
